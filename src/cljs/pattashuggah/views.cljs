(ns pattashuggah.views
  (:require
   [re-frame.core :as re-frame]
   ))

(defn song-toc [song-title]
  [:li song-title])

(defn album-toc [album-data]
  (let [album-title (first album-data)
        songs (second album-data)]
    [:div
     [:h2 album-title]
     [:ol (map song-toc songs)]]))

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
          ["Concatenation"
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
           "Glints Collide"
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
           "In Death - Is Death  1"
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
     (map album-toc disco)
     ]))
