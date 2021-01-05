(ns pattashuggah.db)

(def default-db
  {:disco
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
      {:patterns
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
       ]}]

      ["New Millennium Cyanide Christ"
      {:patterns
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
       ]}]

      ["Corridor Of Chameleons"
      {:patterns
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
       ]}]

      ["Neurotica"
      {:patterns
       ["intro (×4)"
        (str "X-+-+-+- XX-XX-X "
             "X-+-+-+- XX-XX-X "
             "X-+-+-+- XX-XX-X "
             "X-+-+-+-- XX-XX-X "
             "X--")
        "pre verse (×2)"
        (str
          "X--X--XX-XO "
          "X--X--XO "
          "X--X-+O "
          "X--X--XX-XO "
          "X--X--XO "
          "X--X-+O "
          "X--X--XX-XX- "
          )
        "transition (×1)"
        (str (clojure.string/join (repeat 10 "X-- ")) "XO")
        "verse 1 (×4)"
        (str (clojure.string/join (repeat 20 "X-- ")) "X-XO")
        "post verse 1 (×2)"
        (str (clojure.string/join (repeat 6 "X-- ")) "XX-XOX- "
             (clojure.string/join (repeat 7 "X-- ")) "XX-XOX- "
             "X-- X-- X-- XO")
        "nothing"
        "+-+-+-+-"
        "breakdown"
        (str "XOX-+-+-+- X-XO+-+-+-+- "
             "XOX-+-+-+- X-XO+-+-+-+- "
             "XOX-+-+-+- X-XO+-XO+-")
        "pre verse 2"
        (str "X-XX-XOX-- X-XX-X-XOX-- "
             "X-XX-XOX-- X-XX-X-XOX-- "
             "X-XX-XOX-- X-XX-X-XOX ")
        "verse 2"
        "same as pre verse 2"
       ]}]

      ["The Mouth Licking What You've Bled"
      {:patterns
       ["intro (×4)"
        "X-X-- X-X-- X-X-- X-X-X-- X-X-- X-X-- "
        "transition (×1)"
        "X-X-- X-X-- X-X-+-"
        "verse 1 (×1)"
        (str (clojure.string/join (repeat 25 "X-X-- ")) "X--")
        "inter verse (×1)"
        "same as verse"
        "verse 1 bis (×1)"
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
       ]}]
      "Sane"
      "The Exquisite Machinery Of Torture"
      "Elastic"]]

    ["Nothing (2002)"
     ["Stengah"
      "Rational Gaze"
      ["Perpetual Black Second"
      {:patterns
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
       ]}]

      "Closed Eye Visuals"
      ["Glints Collide"
      {:patterns
       ["intro"
        (str "X-+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O "
              "X--+-X-+-")
       ]}]
      "Organic Shadows"
      ["Straws Pulled At Random"
      {:patterns
       ["intro"
        (str "X-X-+X-X-X--+- X-X-+X-X-X--+- "
             "X-X-+X-X-X--+- X-X-+X-X-X--+- "
             "X-X-+X--")
       ]}]
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
      {:patterns
       ["intro (throughout + in this song XX all have an O in between)"
        "XX-"
        "verse 1"
        "XX-"
        "transition (×1)"
        "XX-X-XX-"
        "verse 2"
        "XX-X-"
        ]}]
      "Lethargica"
      "Obzen"
      "This Spiteful Snake"
      "Pineal Gland Optics"
      ["Pravus"
      {:patterns
       ["intro"
        (str "X-+-X-X-+- X-+-X-X-X-+- X-+-X-X-+- X-+-X-X-X-X-+- ")
       ]}]
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
      {:patterns
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
        (str
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-++--+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "XOXO"
             )
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
        (str
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-++--+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-++--+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X--XX-X--XX- "
             "X--XX-X--XX- "
             "X--XX-X- "
             )
       ]}]

      "MonstroCity"
      "By The Ton"
      "Violent Sleep Of Reason"
      "Ivory Tower"
      "Stifled"
      "Nostrum"
      "Our Rage Won't Die"
      "Into Decay"]]
     ]})
