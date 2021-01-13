(ns pattashuggah.views
  (:require
   [re-frame.core :as re-frame]
   [pattashuggah.db :as db]
   ))

(defn rm-wspace [txt]
  (clojure.string/join (remove clojure.string/blank? txt)))

(defn slug [title]
  (clojure.string/lower-case (clojure.string/replace title " " "-")))

(defn pattern-size [pattern]
  (count (rm-wspace pattern)))

(defn pattern-counts [pattern]
  (let [chunks (clojure.string/split pattern " ")]
    (clojure.string/join "+" (map count chunks))))

(defn just-to-see [[k v]]
  (let [times (cond (= v 1) "once"
                    (= v 2) "twice"
                    :else (str v " times"))]
  [:span [:code k] " " times ", "]))

(defn pattern-structure [pattern]
  ; thanks to http://clj-me.cgrand.net/2009/04/27/counting-occurences/
  ; and https://stackoverflow.com/questions/26327715/clojure-sort-map-over-value
  (let [chunks (clojure.string/split pattern " ")
        counts-map (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} chunks)
        sorted  (into (sorted-map-by (fn [key1 key2]
                                         (compare [(get counts-map key2) key2]
                                                  [(get counts-map key1) key1])))
                      counts-map)]
    (map just-to-see counts-map)))

(defn song-toc [song-title]
  (if (string? song-title)  ; song with no pattern -> no link
    [:li song-title]
    (let [title (first song-title)]
      [:li [:a {:href (str "#" (slug title))} title]])))

(defn album-toc [album-data]
  (let [album-title (first album-data)
        songs (second album-data)]
    [:div
     [:h3 [:span album-title]]
     [:ol (map song-toc songs)]]))

(defn count-pattern-maker [n txt]
  (let [alphabet (clojure.string/join (repeat 4 "123456789ABCDEFG"))
        characters (take n alphabet)]
    (clojure.string/join (flatten (map vector characters (repeat txt))))))

(defn positions [character coll]
  ; https://stackoverflow.com/questions/4830900/how-do-i-find-the-index-of-an-item-in-a-vector
  (let [fun (fn [idx x] (when (= character x) idx))]
    (keep-indexed fun coll)))

(defn insert-spaces
  [pos-list txt]
  (if (empty? pos-list)
    txt
    (let [first-space (first pos-list)
          before (clojure.string/join (take first-space txt))
          after (clojure.string/join (drop first-space txt))]
      (insert-spaces (rest pos-list) (str before " " after)))))

(defn pattern [song-pattern notes-per-beat-song-level]
  (let [section-name (first song-pattern)
        pattern-data (second song-pattern)
        pattern (if (string? pattern-data)
                     pattern-data
                     (:pattern pattern-data))
        ; may have to redefine notes-per-beat inherited by the song data
        comments (get pattern-data :comments)
        notes-per-beat (get pattern-data :notes-per-beat
                            notes-per-beat-song-level)
        size (pattern-size pattern)
        spaces (positions " " pattern)
        rulers {2 " "
                3 " '"
                4 " ^ "
                6 " '^ '"
                8 " ' ^ ' "}
        ruler (get rulers notes-per-beat)
        beats (/ size notes-per-beat)
        count-pattern-raw (count-pattern-maker beats ruler)
        count-ruler (insert-spaces spaces count-pattern-raw)]
  [:div
   [:h4 section-name]
   (if (and
         (not (clojure.string/starts-with? pattern "same"))
         (not (clojure.string/starts-with? pattern "TODO")))
     [:div
       [:p {:class "pattern-comments"} comments]
       (cond ; split in several lines?
         (= size 72)  ; Sane has a 8×9 riff
         (let [four-position (clojure.string/index-of count-ruler "4")
               seven-position (clojure.string/index-of count-ruler "7")]
           [:div
             [:pre (subs pattern     0             four-position)]
             [:pre (subs count-ruler 0             four-position)]
             [:pre (subs pattern     four-position seven-position)]
             [:pre (subs count-ruler four-position seven-position)]
             [:pre (subs pattern     seven-position)]
             [:pre (subs count-ruler seven-position)]])
         (or (and (= notes-per-beat 4) (= size 64))
             (= size 96)
             (= size 128))
         (let [nine-position (clojure.string/index-of count-ruler "9")]
           [:div
             [:pre (subs pattern     0 nine-position)]
             [:pre (subs count-ruler 0 nine-position)]
             [:pre (subs pattern     nine-position)]
             [:pre (subs count-ruler nine-position)]])
         (= size 104)  ; Soul Burn has a 8×13 riff
         (let [e-position (clojure.string/index-of count-ruler "D")
               to-be-tweaked (subs count-ruler e-position)
               match #"[123456789A]"
               str1 "123456789A"
               str2 "HIJKLMNOPQ"
               replace-map (into (sorted-map) (map vector str1 str2))
               count-ruler-tweaked
                (clojure.string/replace to-be-tweaked match replace-map)]
           [:div
             [:pre (subs pattern     0 e-position)]
             [:pre (subs count-ruler 0 e-position)]
             [:pre (subs pattern     e-position)]
             [:pre count-ruler-tweaked]])
         (= size 256)
         (let [one-pos (positions "1" count-ruler)
               split-2 (second one-pos)
               nine-pos (positions "9" count-ruler)
               split-1 (first nine-pos)
               split-3 (second nine-pos)]
           [:div
             [:pre (subs pattern     0       split-1)]
             [:pre (subs count-ruler 0       split-1)]
             [:pre (subs pattern     split-1 split-2)]
             [:pre (subs count-ruler split-1 split-2)]
             [:pre (subs pattern     split-2 split-3)]
             [:pre (subs count-ruler split-2 split-3)]
             [:pre (subs pattern     split-3)]
             [:pre (subs count-ruler split-3)]])
         (= size 512)
         (let [one-pos (positions "1" count-ruler)
               split-2 (second one-pos)
               split-4 (nth one-pos 2)
               split-6 (nth one-pos 3)
               nine-pos (positions "9" count-ruler)
               split-1 (first nine-pos)
               split-3 (second nine-pos)
               split-5 (nth nine-pos 2)
               split-7 (nth nine-pos 3)]
           [:div
             [:pre (subs pattern     0       split-1)]
             [:pre (subs count-ruler 0       split-1)]
             [:pre (subs pattern     split-1 split-2)]
             [:pre (subs count-ruler split-1 split-2)]
             [:pre (subs pattern     split-2 split-3)]
             [:pre (subs count-ruler split-2 split-3)]
             [:pre (subs pattern     split-3 split-4)]
             [:pre (subs count-ruler split-3 split-4)]
             [:pre (subs pattern     split-4 split-5)]
             [:pre (subs count-ruler split-4 split-5)]
             [:pre (subs pattern     split-5 split-6)]
             [:pre (subs count-ruler split-5 split-6)]
             [:pre (subs pattern     split-6 split-7)]
             [:pre (subs count-ruler split-6 split-7)]
             [:pre (subs pattern     split-7)]
             [:pre (subs count-ruler split-7)]
            ])
         :else
         [:div
           [:pre pattern]
           [:pre count-ruler]])
       [:p {:class "size"} "Size : " size " "
           "(" (pattern-counts pattern)  ")"]
       [:p {:class "structure"} "Structure : " (pattern-structure pattern)  ]]
     ; for «same» or «TODO» patterns
     [:p {:class "same"} pattern])]))

(defn song-with-patterns [song-and-patterns]
  (let [title (first song-and-patterns)
        data-map (second song-and-patterns)
        notes-per-beat (get data-map :notes-per-beat 8)
        comments (:comments data-map)
        patterns (partition 2 (:patterns data-map))]
    [:div
     [:h3 {:id (slug title)} [:span title]]
     [:p {:class "song-comments"} comments]
     (map pattern patterns (repeat notes-per-beat))
     ]))

(defn patterns [album-data]
  (let [album-title (first album-data)
        songs (second album-data)]
     (map song-with-patterns (filter (complement string?) songs))))

(defn main-panel []
  (let [disco (:disco db/default-db)]
    [:div
     [:h1 "Pattashuggah"]
     [:p "Rhythm patterns from some "
         [:a {:href "https://meshuggah.net" :target "_blank"} "Meshuggah"]
         " songs. Contribute to the "
         [:a {:href "https://github.com/grahack/pattashuggah"
              :target "_blank"} "Github project"]
         "."]
     [:p [:strong "Important notice"] " : "
         "These are not drums scores. By far."]
     [:p "Choices were made to make these patterns good hands exercises. "
         "Try to beat ostinatos with your feet though (16ths, 8ths, quarters "
         "or even half notes), or keep a steady beat with your weak foot and "
         "play the kick in unison with your strong hand."]
     [:p "Better yet : count the beats out loud (8ths, quarters…) ala "
         [:a {:href "https://en.wikipedia.org/wiki/Konnakol"
              :target "_blank"} "Konnakol"] " (cf "
         [:a {:href "https://soundcloud.com/profgra-org/exo-5-ala-mouth-licking-xoxoo"
              :target "_blank"} "this clip"] ")."]
     [:p "The following table gives you the meaning of the characters used "
      "in a pattern description. Space characters are only used to group "
      "by meaningful chunks."]
     [:table
       [:thead
         [:tr [:th "Code"] [:th "Right lead"] [:th "Left lead"]]]
       [:tbody
         (list [:tr (list [:td "X"] [:td "R"] [:td "L"])]
               [:tr (list [:td "O"] [:td "L"] [:td "R"])]
               [:tr (list [:td "+"] [:td "r"] [:td "l"])]
               [:tr (list [:td "-"] [:td "l"] [:td "r"])])]]
     [:p "Right below each pattern is a « count ruler » "
         "to help you navigate in the pattern :"]
     [:pre "X--X-X-X-+ X--X-X-X-X-+ X--X-X-X-+ X--X-X-X-X-+   ..."]
     [:pre "1 ' ^ ' 2  ' ^ ' 3 ' ^  ' 4 ' ^ '  5 ' ^ ' 6 '    ..."]
     [:p "Patterns of 128 notes or more are displayed on several lines. "
         "The split occurs brutally at the ninth beat (or similar beat) "
         "even if it splits a chunk. "
         "Usually at this place in the pattern nothing unexpected happens."]
     [:h2 "Table of contents"]
     (map album-toc disco)
     [:h2 "Patterns"]
     (map patterns disco)
     ]))
