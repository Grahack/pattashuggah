(ns pattashuggah.views
  (:require
   [re-frame.core :as re-frame]
   ))

(defn rm-wspace [txt]
  (clojure.string/join (remove clojure.string/blank? txt)))

(defn slug [title]
  (clojure.string/lower-case (clojure.string/replace title " " "-")))

(defn pattern-size [pattern]
  (count (rm-wspace pattern)))

(defn pattern-counts [pattern]
  (let [splitted (clojure.string/split pattern " ")]
    (clojure.string/join "+" (map count splitted))))

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
  (let [characters (take n "123456789ABCDEFG")]
    (clojure.string/join (flatten (map vector characters (repeat txt))))))

(defn positions [pred coll]
  ; https://stackoverflow.com/questions/4830900/how-do-i-find-the-index-of-an-item-in-a-vector
  (keep-indexed (fn [idx x]
         (when (pred x) idx)) coll))

(defn insert-spaces
  [pos-list txt]
  (if (empty? pos-list)
    txt
    (let [first-space (first pos-list)
          before (clojure.string/join (take first-space txt))
          after (clojure.string/join (drop first-space txt))]
      (insert-spaces (rest pos-list) (str before " " after)))))

(defn pattern [song-pattern]
  (let [section (first song-pattern)
        pattern (second song-pattern)
        size (pattern-size pattern)
        spaces (positions clojure.string/blank? pattern)
        count-patterns
          {16 (count-pattern-maker  2 " ' ^ ' ")
           24 (count-pattern-maker  3 " ' ^ ' ")
           32 (count-pattern-maker  4 " ' ^ ' ")
           64 (count-pattern-maker  8 " ' ^ ' ")
          128 (count-pattern-maker 16 " ' ^ ' ")}
        count-pattern-raw (get count-patterns size)
        count-ruler (insert-spaces spaces count-pattern-raw)]
  [:div
   [:h4 section]
   (if (and
         (not (clojure.string/starts-with? pattern "same"))
         (not (clojure.string/starts-with? pattern "TODO")))
     [:div
       (if (= size 128)  ; split in two lines
         (let [nine-position (clojure.string/index-of count-ruler "9")]
           [:div
             [:pre (subs pattern     0 nine-position)]
             [:pre (subs count-ruler 0 nine-position)]
             [:pre (subs pattern     nine-position)]
             [:pre (subs count-ruler nine-position)]])
         [:div
           [:pre pattern]
           [:pre count-ruler]])
       [:p {:class "size"} "Size : " size " "
           "(" (pattern-counts pattern)  ")"]]
     [:p {:class "same"} pattern])]))

(defn song-with-patterns [song-and-patterns]
  (let [title (first song-and-patterns)
        patterns (partition 2 (second song-and-patterns))]
    [:div
     [:h3 {:id (slug title)} [:span title]]
     (map pattern patterns)
     ]))

(defn patterns [album-data]
  (let [album-title (first album-data)
        songs (second album-data)]
     (map song-with-patterns (filter (complement string?) songs))))

(defn main-panel []
  (let [disco
        [["Contradictions Collapse (1991)"
          ["Paralyzing Ignorance"
           "Erroneous Manipulation"
           "Abnegating Cecity"
           "Internal Evidence"
           "Qualms Of Reality"
           "We'll Never See The Day"
           "Greed"
           "Choirs Of Devastation"
           "Cadaverous Mastication"]]

         ["Destroy Erase Improve (1995)"
          ["Future Breed Machine"
           "Beneath"
           "Soul Burn"
           "Transfixion"
           "Vanished"
           "Acrid Placidity"
           "Inside What's Within Behind"
           "Terminal Illusions"
           "Suffer In Truth"
           "Sublevels"]]

         ["Chaosphere (1998)"
          [["Concatenation"
            ["intro (×4)"
             (str "X--X-X-X-- X--X-X-X-- X--X-X-X-- X--X-X-X-- "
                  "X--X-X-X-- X--X-X-X-- X-+-")
             "verse 1 (×4)"
             (str "X--X-X-X-+ X--X-X-X-+ X--X-X-X-+ X--X-X-X-+ "
                  "X--X-X-X-+ X--X-X-X-+ X-+-")
             "chorus 1 (×2)"
             "same as intro"
             "intro 2 (×2)"
             "same as intro"
             "verse 2 (×4)"
             (str "X--X-X-X-+ X--X-X-X-X-+ X--X-X-X-+ X--X-X-X-X-+ "
                  "X--X-X-X-+ X--X-X-X-+")
             "chorus 2 (×2)"
             "same as intro"
             "intro 3 (×2)"
             "same as verse 1"
             "guitar solo (×4)"
             (str "XOXOX-XOXOX-- XOXOX-XOXOX-- XOXOX-XOXOX-- XOXOX-XOXOX-- "
                  "XOXOX-XOXOX-")
             "pre bridge (×4)"
             (str "X--X--X--X-+- X--X--X--X-+- X--X--X--X-+- X--X--X--X-+- "
                  "X--X--X--X-- ")
             "bridge (×4)"
             "same as guitar solo"
             "pre chorus"
             "X--X-X-X-- X--X-X-X-- +-+-"
             "chorus 3 (×4)"
             "same as intro"
             "outro (×2)"
             "same as verse (with a premature end though)"
            ]]
           ["New Millennium Cyanide Christ"
            ["intro (×2)"
             (str "XOXO+-X-X- "
                  "XOXO+-X-X- XOXOXOX--X-X- "
                  "XOXO+-X-X- XOXOXOX--X-X- "
                  "XOXO+-X-X- XOXOXOX--X-X- "
                  "XOXO+-X-X- XOXOXOX--X-X- "
                  "XOXO+-X-X- XOXOXOX--X-X- OXO ")
             "verse 1 (×4)"
             (str "XOXO+-X--X-- XOXO+-X-X-+- "
                  "XOXO+-X--X-- XOXO+-X-X-+- "
                  "XOXO+-X-X- XOXO+- ")
             "post verse 1 (×2, last one without the last note) "
             (str "XOXO+-X--X-- XOXO+-X-- "
                  "XOXO+-X--X-- XOXO+-X-- "
                  "XOXO+-X--X-- XOXO+-X-+O ")
             "pre verse 2 (×4)"
             "same as verse 1"
             "verse 2 (×4)"
             "same as verse 1"
             "post verse 2"
             "same as post verse 1 + a silence lasting one eight note"
             "intro 2 (×2)"
             "same as intro"
             "bridge pre guitar solo (×4, last one without the three last notes)"
             (str "XOXOX-- "
                  "XOXOXOX-- XOXOXOX-- XOXOXOX-- "
                  "XOXOXOX-- XOXOXOX-- XOXOXOX-- "
                  "XXO")
             "guitar solo (×2)"
             "same as bridge (×2)"
             "end of guitar solo (×1)"
             (str "XOXOX-- "
                  "XOXOXOX-- XOXOXOX-- XOXOXOX-- "
                  "XOXOXOX-- XOXOXOX-- XOXOXOX-- "
                  "XOXOXOX-- XOXOXOX-- XOXOXOX-- "
                  "XOXOXOX-- XOXOXOX-- XOXOXOX-- "
                  "XOXOOX-+-+-+- ")
             "bridge post guitar solo (×4)"
             (str "X--X--X-+-+-+-+- X--X--X--X--X--X--X--X--+-+-+-+- "
                  "X--X--X--X--X-+-")
             "same bridge but instrumental and three notes accented (×4)"
             (str "X--X--X-+-+-+-+- X--X--X--X--X--X--X--X--X-+-+-+- "
                  "X--X--X--X--X-XO")
             "end riff (until fade out)"
             (str "X--X--XOX-+- X--X--XOXOXOX-+- "
                  "X--X--XOX-+- X--X--XOXOXOX-+- "
                  "X--X--XO")
            ]]
           ["Corridor Of Chameleons"
            ["intro (×4)"
             (str "XOXOXOX-+- XOXOXOX-+- XOXOXOX-+- XOXOXOX-+- "
                  "XOXOXOX-+- XOXOXOX-+- XOXO")
             "verse 1 (×4)"
             "same as intro"
             "chorus 1 (×3)"
             (str "XOXOXOX-+- XOXOXOX-+- XOXOXOX-+- XO")
             "chorus 1, last line (×1)"
             (str "XOXOXOX-+- XOXOXOX-+- XOXOXOXOXO XO")
             "verse 2 (×4)"
             "same as intro"
             "pre guitar solo (×4)"
             (str "X-+-X-+-X- X-+-X-+-X- "
                  "X-+-X-+-X- X-+-X-+-X- "
                  "X-+-X-+-X- X-+-X-+-X- "
                  "X-+-")
             "guitar solo (×2)"
             (str "X-+-X-+-X- X-+-X-+-X- X-+-X- X-+-X-+-X- X-+-X-+-X-+-X- "
                  "X-+-X-+-X- X-+-X-+-X- X-+-X- X-+-X-+-X- X-+-X-+-X-+-X- "
                  "X-+-X-+-X- X-+-X-+-X- X-+-X- X-")
             "post guitar solo (×2)"
             "same as guitar solo"
             "bridge, with Jens (×2)"
             (str "+-+-X-X-+-+-+-X-X-+-+-+-X-X-+-X-X-+- "
                  "+-+-X-X-+-+-+-X-X-+-+-+-X-X-+-X-X-+- "
                  "+-+-X-X-+-+-+-X-X-+-+-+-X-X-+-X-X-+- "
                  "+-+-X-X-+-+-+-X-X-+-")
             "bridge, instrumental (×2)"
             "same as bridge"
             "end of bridge (×1)"
             "X-X-+-+-+- X-X-+-"
             "bridge 2 (×2)"
             (str "X-X-X-+-X-X-+-X-+- X-X-X-+-X-X-X-+-X-+- "
                  "X-X-X-+-X-X-+-X-+- X-X-X-+-X-X-X-+-X-+- "
                  "X-X-X-+-X-X-+-X-+- X-X-X-+-X-X-X-+-X-+- "
                  "X-X-X-+-X-X-X-")
             "bridge, instrumental (×2)"
             "same as bridge"
             "post guitar solo (until fade out)"
             "same as post guitar solo"
            ]]
           "Neurotica"
           "The Mouth Licking What You've Bled"
           "Sane"
           "The Exquisite Machinery Of Torture"
           "Elastic"]]

         ["Nothing (2002)"
          ["Stengah"
           "Rational Gaze"
           ["Perpetual Black Second"
            ["intro (×2)"
             (str "X--X--X-XX-X-- X--X--X-XX-X-- X--X--X-XX-X-- X--X--X-XX-X-- "
                  "X--X--X-XX-X-- X--X--X-XX-X-- X--X--X-XX-X-- X--X--X-XX-X-- "
                  "X--X--X-XX-X-- X-")
             "pre verse (×2)"
             (str "X- X--X--X--X--X-X-X- X--X--X--X--X-X-X- X--X--X--X--X-X-X- "
                     "X--X--X--X--X-X-X- X--X--X--X--X-X-X- X--X--X--X--X-X-X- "
                     "X--X--X--X--X-X-X-")
             "verse 1 (×2)"
             (str "X- X--X--X-X-X--X--X- X--X--X-X-X--X--X- X--X--X-X-X--X--X- "
                     "X--X--X-X-X--X--X- X--X--X-X-X--X--X- X--X--X-X-X--X--X- "
                     "X--X--X-X-X--X--X-")
             "intro 2 (×1)"
             "same as intro"
             "verse 2 (×2)"
             "same as verse 1"
             "post verse (×2)"
             "same as pre verse"
             "pre guitar solo"
             "TODO"
             "guitar solo (×4)"
             (str "X-X-+-X--X--+- X-X-+-X--X--+- X-X-+-X--X--+- "
                  "X-X-+-X--X--+- X-X-+-+-")
             "last verse, same pattern but longer the wait (×2)"
             (str "X-X-+-X--X--+- X-X-+-X--X--+- X-X-+-X--X--+- "
                  "X-X-+-X--X--+- X-X-+-X--X--+- X-X-+-X--X--+- "
                  "X-X-+-X--X--+- X-X-+-X--X--+- X-X-+-X--X--+- X-")
             "end"
             "TODO"
            ]]
           "Closed Eye Visuals"
           ["Glints Collide"
            ["intro"
             (str "X-+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O "
                   "X--+-X-+-")
            ]]
           "Organic Shadows"
           "Straws Pulled At Random"
           "Spasm"
           "Nebulous"
           "Obsidian"]]

         ["Catch Thirtythree (2005)"
          ["Autonomy Lost"
           "Imprint Of The Un-Saved"
           "Disenchantment"
           "The Paradoxical Spiral"
           "Re-Inanimate"
           "Entrapment"
           "Mind's Mirrors"
           "In Death - Is Life"
           "In Death - Is Death"
           "Shed"
           "Personae Non Gratae"
           "Dehumanization"
           "Sum"]]

         ["obZen (2008)"
          ["Combustion"
           "Electric Red"
           "Bleed"
           "Lethargica"
           "Obzen"
           "This Spiteful Snake"
           "Pineal Gland Optics"
           "Pravus"
           "Dancers To A Discordant System"]]

         ["Koloss (2012)"
          ["I Am Colossus"
           "The Demon's Name Is Surveillance"
           "Do Not Look Down"
           "Behind The Sun"
           "The Hurt That Finds You First"
           "Marrow"
           "Break Those Bones Whose Sinews Gave It Motion"
           "Swarm"
           "Demiurge"
           "The Last Vigil"]]

         ["The Violent Sleep Of Reason (2016)"
          ["Clockworks"
           "Born In Dissonance"
           "MonstroCity"
           "By The Ton"
           "Violent Sleep Of Reason"
           "Ivory Tower"
           "Stifled"
           "Nostrum"
           "Our Rage Won't Die"
           "Into Decay"]]
          ]]
    [:div
     [:h1 "Pattashuggah"]
     [:p "Rhythm patterns from some "
         [:a {:href "https://meshuggah.net"} "Meshuggah"]
         " songs. Contribute to the "
         [:a {:href "https://github.com/grahack/pattashuggah"} "Github project"]
         "."]
     [:p [:strong "Important notice"] " : "
         "These are not drums scores. By far."]
     [:p "Choices were made to make these patterns good hands exercises. "
         "Try to beat ostinatos with your feet though (16ths, 8ths, quarters "
         "or even half notes), or keep a steady beat with your weak foot and "
         "play the kick in unison with your strong hand."]
     [:p "Better yet : count the beats out loud (8ths, quarters…)."]
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
     [:p "Patterns of 128 notes are displayed on two lines. The split occurs "
         "brutally at the ninth beat even if it splits a chunk. "
         "Usually at this place in the pattern nothing unexpected happens."]

     [:h2 "Table of contents"]
     (map album-toc disco)
     [:h2 "Patterns"]
     (map patterns disco)
     ]))
