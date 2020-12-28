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
     [:h3 album-title]
     [:ol (map song-toc songs)]]))

(defn pattern [song-pattern]
  (let [section (first song-pattern)
        pattern (second song-pattern)
        size (pattern-size pattern)]
  [:div
   [:h4 section]
   [:pre pattern]
   [:p {:class "size"} "Size : " size " "
       "(" (pattern-counts pattern)  ")"]]))

(defn song-with-patterns [song-and-patterns]
  (let [title (first song-and-patterns)
        patterns (second song-and-patterns)]
    [:div
     [:h3 {:id (slug title)} title]
     (map pattern patterns)
     ]))

(defn patterns [album-data]
  (let [album-title (first album-data)
        songs (second album-data)]
     (map song-with-patterns (filter (complement string?) songs))))

(defn main-panel []
  (let [disco
        [["Contradictions Collapse"
          ["Paralyzing Ignorance"
           "Erroneous Manipulation"
           "Abnegating Cecity"
           "Internal Evidence"
           "Qualms Of Reality"
           "We'll Never See The Day"
           "Greed"
           "Choirs Of Devastation"
           "Cadaverous Mastication"]]

         ["Destroy Erase Improve"
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

         ["Chaosphere"
          [["Concatenation"
            (sorted-map
              "intro"
              (str "X--X-X-X-- X--X-X-X-- X--X-X-X-- X--X-X-X-- "
                   "X--X-X-X-- X--X-X-X-- X-+-")
              "verse 1"
              (str "X--X-X-X-+ X--X-X-X-+ X--X-X-X-+ X--X-X-X-+ "
                   "X--X-X-X-+ X--X-X-X-+ X-+-"))]
           "New Millennium Cyanide Christ"
           "Corridor Of Chameleons"
           "Neurotica"
           "The Mouth Licking What You've Bled"
           "Sane"
           "The Exquisite Machinery Of Torture"
           "Elastic"]]

         ["Nothing"
          ["Stengah"
           "Rational Gaze"
           "Perpetual Black Second"
           "Closed Eye Visuals"
           ["Glints Collide"
            (sorted-map
              "intro"
              (str "X-+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O "
                   "X--+-X-+-"))]
           "Organic Shadows"
           "Straws Pulled At Random"
           "Spasm"
           "Nebulous"
           "Obsidian"]]

         ["Catch Thirtythree"
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

         ["obZen"
          ["Combustion"
           "Electric Red"
           "Bleed"
           "Lethargica"
           "Obzen"
           "This Spiteful Snake"
           "Pineal Gland Optics"
           "Pravus"
           "Dancers To A Discordant System"]]

         ["Koloss"
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

         ["The Violent Sleep Of Reason"
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
     [:h2 "Table of contents"]
     (map album-toc disco)
     [:h2 "Patterns"]
     (map patterns disco)
     ]))
