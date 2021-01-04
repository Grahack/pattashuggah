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
  (let [alphabet (clojure.string/join (repeat 4 "123456789ABCDEFG"))
        characters (take n alphabet)]
    (clojure.string/join (flatten (map vector characters (repeat txt))))))

(defn positions [character coll]
  ; https://stackoverflow.com/questions/4830900/how-do-i-find-the-index-of-an-item-in-a-vector
  (keep-indexed (fn [idx x]
         (when (= character x) idx)) coll))

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
        spaces (positions " " pattern)
        count-patterns
          {16 (count-pattern-maker  2 " ' ^ ' ")
           24 (count-pattern-maker  3 " ' ^ ' ")
           32 (count-pattern-maker  4 " ' ^ ' ")
           64 (count-pattern-maker  8 " ' ^ ' ")
          128 (count-pattern-maker 16 " ' ^ ' ")
          256 (count-pattern-maker 32 " ' ^ ' ")
          512 (count-pattern-maker 64 " ' ^ ' ")}
        count-pattern-raw (get count-patterns size)
        count-ruler (insert-spaces spaces count-pattern-raw)]
  [:div
   [:h4 section]
   (if (and
         (not (clojure.string/starts-with? pattern "same"))
         (not (clojure.string/starts-with? pattern "TODO")))
     [:div
       (cond ; split in several lines?
         (= size 128)
         (let [nine-position (clojure.string/index-of count-ruler "9")]
           [:div
             [:pre (subs pattern     0 nine-position)]
             [:pre (subs count-ruler 0 nine-position)]
             [:pre (subs pattern     nine-position)]
             [:pre (subs count-ruler nine-position)]])
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
           ["The Mouth Licking What You've Bled"
            ["intro (×4)"
             "X-X-- X-X-- X-X-- X-X-X-- X-X-- X-X-- "
             "transition (×1)"
             "X-X-- X-X-- X-X-+-"
             "verse 1 (throughout)"
             (str (clojure.string/join (repeat 25 "X-X-- ")) "X--")
             "inter verse"
             "same as verse"
             "verse 1 bis"
             "same as verse"
             "chorus (×4)"
             "same as intro"
             "transition (×1)"
             "X-X-- X-X-- X-X-+-"
             "guitar solo"
             (str "X-X-- X-X-- X-X-X-X-- "
                  "X-X-- X-X-- X-X-X-X-- "
                  "X-X-- X-X-- X-X-X-X-- "
                  "X-X-- X- ")
             "post guitar solo, sang"
             "same as verse"
             "post guitar solo, instrumental (×1)"
             "same as guitar solo"
             "post guitar solo, sang"
             "same as verse"
             "bridge (×4, half instrumental, half sang)"
             (str
               "XOX-OXOX-OXOXO "
               "XOX-OXOX-OXOX-OXOXO "
               "XOX-OXOX-OXOX-OXOXO "
               "XOX-OXOX-OXO ")
             "pre end (×4)"
             (str
               "X-XOOX--X-- "
               "X-+XOX--X-- "
               "X-XOOX--X-- "
               "X-+XOX--X-- "
               "X-XOOX--X-- "
               "X-+XOX-+- ")
             "end riff (until fade out)"
             (str
               "X--X-XX-- "
               "X--X-XX-- "
               "X--X-XX-- "
               "X--X-XX-- "
               "X--X-XX-- "
               "X--X-XX-- "
               "X--X-XX-+- "
               )
            ]]
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
           ["Straws Pulled At Random"
            ["intro"
             (str "X-X-+X-X-X--+- X-X-+X-X-X--+- "
                  "X-X-+X-X-X--+- X-X-+X-X-X--+- "
                  "X-X-+X--")
            ]]
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
           ["Bleed"
            ["intro (throughout + in this song XX all have an O in between)"
             "XX-"
             "verse 1"
             "XX-"
             "transition (×1)"
             "XX-X-XX-"
             "verse 2"
             "XX-X-"
             ]]
           "Lethargica"
           "Obzen"
           "This Spiteful Snake"
           "Pineal Gland Optics"
           ["Pravus"
            ["intro"
             (str "X-+-X-X-+- X-+-X-X-X-+- X-+-X-X-+- X-+-X-X-X-X-+- ")
            ]]
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
           ["Born In Dissonance"  ; xtO3VCu5wv4
            ["intro (×2)"
             (str "X--XX-X- X--XX-X-X- X--XX-X-X-X- X--XX-X-X-X- X--XX-X-X-X- "
                  "X--XX- X--XX-X- X--XX-X-X- X--XX-X-X-X- X--XX-X-X-X- "
                  "X--XX-X-X-X- X--XX- X--XX-X- ")
             "verse 1 (×2)"
             "same as intro with different notes"
             "post verse 1 (×1)"
             (str "X--XX- X--XX- X--XX- X--XX- X--XX- X--XX- XOXO "
                  "X--XX- X--XX- X--XX- X--XX- X--XX- X--XX- XOXO "
                  "X--XX- X--XX- X--XX- X--XX- X--XX- X--XX- XOXO "
                  "X--XX- X-")
             "verse 2 (×2)"
             "same as verse 1"
             "post verse 2 (×1)"
             "TODO"
             ; this falls right into a 128 notes pattern:
             ;(str "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X-")  ; and Jens arrives right after,
             ; but the pattern continues over the change of section.
             ; this fits but there are some weird notes near the end:
             ;(str "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     ;         ^ here?
             ;     "XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- "
             ;     "XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X--XX- X--XX- XO X--XX- XO X--XX- "
             ;     "X--XX- X--XX- X-+-")
             "bridge"
             "TODO but try to play the intro pattern"
             "guitar solo (×2)"
             "same as intro"
             "post guitar solo (×1)"
             (str "X--XX-X--X--+-+-+-+-+-+-+- "
                  "X--XX-X--X--X--X--X-+-+-+-+-+-+- "
                  "X--XX-X--X--+-+-+-+-+-+-+- "
                  "X--XX-X--X--X--X--X-+-+-+-+-+-+- "
                  "+-+-+-+- "
                  "X--XX-X--X--+-+-+-+-+-+-+- "
                  "X--XX-X--X--X--X--X-+-+-+-+-+-+- "
                  "X--XX-X--X--+-+-+-+-+-+-+- "
                  "X--XX-X--X--X--X--X-+-+-+-+-+-+- "
                  "+-+OXOXOX-+-+-+- ")
             "end verse"
             (str "X--XX- X--XX- X-X-X- X--XX- X--XX- X--XX- X-X-X-X- "
                  "X--XX- X--XX- X-X-X- X--XX- X--XX- X--XX- X-X-X-X- "
                  "X--XX- X--XX- X-X-X- X--XX- X--XX- X--XX- X-X-X-X- "
                  "X--XX- X--XX- X-X-X- X--XX- X--XX- X--XX- X-X-X-X- "
                  "X--XX- X--XX- X-X-X- X--XX- X--XX- X--XX- X-X-X-X- "
                  "X--XX- X--XX- X-X-X- X--XX- X-+- X-+-+-+-")
             "outro"
             "TODO"
            ]]
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
