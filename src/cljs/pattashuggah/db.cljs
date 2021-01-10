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
      ["Beneath"
       {:notes-per-beat 6
        :patterns
        ["pre intro, slow without drums (×1)"
         (str "X--+-- X--+-- X--X-- X--X-- "
              "X--+-- X--X-- X--X-- X--X-- "
              "X--+-- X--+-- X--+-- X-X-X- "
              "X--+-- X--X-- X--+-- X-X-X- ")
         "intro (×4)"
         (str "X-+-XX-XO "
              "X-+-XX-XO "
              "X-+-XX-XO "
              "X--XOOXOO ")
         "pre verse 1 (×4, 2 without guitars, 2 with)"
         {:notes-per-beat 3
          :pattern (str (clojure.string/join (repeat 9 "X-X-- ")) "X--")}
         "verse 1 (×4)"
         "same as pre verse 1"
         "post verse  1 (×2)"
         {:notes-per-beat 3
          :pattern "XXOXOX-- XXOXOX-- XXOXOXOO XXOX-- XXOXOX- XXOXOXOXOXO"}
         "pre chorus 1 (×1)"
         (str (clojure.string/join (repeat 10 "X--+--+-- ")) "X--+--")
         "chorus 1 half-time (×4)"
         "X--+-X-X-X--+-+X-X-X-+-- "
         "chorus 1 double-time (×4)"
         {:notes-per-beat 3
          :pattern "X--+-X-X-X--+-+X-X-X-+-- "}
         "pre verse 2 (×2, 1 without the ride, 1 with)"
         "same as pre verse 1"
         "verse 2 (×2)"
         "same as verse 1"
         "guitar solo (×4)"
         {:notes-per-beat 3
          :pattern (str (clojure.string/join (repeat 9 "XOX-+ ")) "X--")}
         "post verse 2 (×2)"
         "same as post verse 1"
         "intro again (×2)"
         "same as intro"
         "intro again stronger (×1)"
         (clojure.string/join (repeat 4 "X-X-XOX-- "))
         "intro again stronger but ending (×1)"
         "X-X-XOX-- X-X-XOX-- X-X-XOX-X -+-X-+-X- "
         "interlude (×4)"
          "X--XX--XOX-- XX--XX--XOX-- XX--XX--XOX-- XX--XX--XO "
         "post interlude transition (×4)"
         {:notes-per-beat 3
          :pattern "XX--XOX--"}
         "verse 3 (×4)"
         "same as verse 1"
         "post verse 3 (×2)"
         "same as post verse 1"
         "chorus 2 half-time (×2)"
         "same as chorus 1 half-time"
         "chorus 1 double-time (×2)"
         "same as chorus 1 double-time"
         "outro"
         (let [nine-times-five (clojure.string/join (repeat 5 "X--+--+-- "))]
           (str nine-times-five "+-- " nine-times-five "X-X"))
         ]}]

      ["Soul Burn"
       {:notes-per-beat 4
        :patterns
        ["song intro 1 (×2)"
         (str "X--X-- +-+-+- "
              "X--X-- +-+-+- "
              "X--X-- +-+-+- "
              "X--X-- X--X-- ")
         "small addition, the pulse skips an eighth note (×1)"
         "X--X-- "
         "song intro with backbeat (×2)"
         (str "X-+-+- +--+-- "
              "X-+-+- +--+-- "
              "X-+-+- +--+-- "
              "+--+-- +--+-- ")
         "song intro with weird snare hits (×3)"
         (str "X-+-+- +--+-O "
              "X-+OX- +--+-- "
              "X-+-+- +--+-- "
              "+--+-- +--+-- ")
         "song intro, last time, another eighth skipped (×1)"
         (str "X-+-+- +--+-O "
              "X-+OX- +--+-- "
              "X-+-+- +--+-- ")
         "verse intro (×1)"
         (clojure.string/join (repeat 2 "X-X-+- X-X-+- X-X-+- X-X-X-+- "))
         "verse 1 (×2)"
         (clojure.string/join (repeat 4 "X-X-+- X-X-+- X-X-+- X-X-X-+- "))
         "post verse 1 (×1)"
         "same as verse 1"
         "verse 2, first time (×1)"
         "same as verse 1"
         "verse 2, second time (×1)"
         (str (clojure.string/join (repeat 3 "X-X-+- X-X-+- X-X-+- X-X-X-+- "))
              "X-X-+- X-X-+- X-X-+- ")
         "end of verse 2"
         "X--X--X--X--+-+- "
         "small addition, the pulse skips an eighth note (×1)"
         "X--X-- "
         "intro for verse 3"
         (str "+-+-+- X--X-- +-XOXO X--X--")
         "verse 3, but with the riff of the intro with backbeat (×2)"
         "same as song intro with backbeat"
         "post verse 3, first time (×1)"
         "same as song intro with weird snare hits"
         "post verse 3, second time (×1)"
         (str "X-+-+- +--+-O "
              "X-+OX- +--+-- "
              "X-+-+- +--+-- "
              "+--+-- +--+-- ")
         "pre guitar solo (×8)"
         "X-+X-X-X-+X-+X-+X-X-+X-X-X-+X-+-"
         "guitar solo part 1 (×3)"
         "same as pre guitar solo"
         "guitar solo part 1, last (×1)"
         "same as pre guitar solo"
         "guitar solo part 2 (×6)"
         "+X--X-+-X--X-+-X--+-+-X--X-+-X--X--XX-XX-+-X--X--X-+-XX-X--X--+-"
         "verse 4"
         "todo"
         "post verse 4"
         "todo"
         "pre bridge (×4)"
         "XX-XX-XOXOX-"
         "bridge, instrumental (×8)"
         "same as pre bridge"
         "bridge, sang (×8)"
         "same as pre bridge"
         "post bridge 1 (×1)"
         (str (clojure.string/join (repeat 16 "X-X-- ")) "X-+-X-+-X-+-")
         "post bridge 2 (×1)"
         (str (clojure.string/join (repeat 16 "X-X-- ")) "X-+-X-+-X-+-")
         "pre outro"
         "same as pre guitar solo"
         ]}]

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
      {:notes-per-beat 4
       :patterns
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
        "nothing (×1)"
        "+-+-+-+-"
        "breakdown (×1)"
        (str "XOX-+-+-+- X-XO+-+-+-+- "
             "XOX-+-+-+- X-XO+-+-+-+- "
             "XOX-+-+-+- X-XO+-XO+-")
        "pre verse 2 (×2)"
        (str "X-+X-XOX-- X-++-X-XOX-- "
             "X-+X-XOX-- X-++-X-XOX-- "
             "X-+X-XOX-- X-++-X-XOO ")
        "verse 2 (×2)"
        "same as pre verse 2"
        "guitar solo (×4)"
        (str "X-+-OX-X-- "
             "X-+-OX-X-++OX-X-- "
             "X-+-OX-X-++OX-X-- "
             "X-+-OX-X-++OX-X-- "
             "X-- ")
        "transition 2 (×1)"
        "+-+-XO+-"
        "intro 2"
        "same as intro (×2)"
        "verse 3 (×4)"
        "same as verse 1"
        "post verse 3 (×2)"
        "same as post verse 1"
        "pre outro (×2)"
        "same as pre verse 2"
        "outro (×56)"
        "X--"
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

      ["Sane"
      {:patterns
       ["intro (×8)"
        "X-- X-- X-- X-- X-XO"
        "verse 1 (×4)"
        (str "X- " (clojure.string/join (repeat 9 "+XO ")) "+X-")
        "post verse 1 (×2)"
        "X--X--+X-X--X-+- X--X--X--X--X-+-"
        "transition"
        "X-+-+-+-"
        "verse 2 (×4)"
        "same as verse 1"
        "post verse 2 (×4)"
        "X-- X-- X-- X-- X-XO"
        "pre guitar solo (×8)"
        "XOOX- XOOX- XOOX- + "
        "guitar solo (×4)"
        "same as verse"
        "pre chorus 1 (×8)"
        "same as pre guitar solo"
        "chorus 1 (×8)"
        {:notes-per-beat 2
         :pattern "X-+-XX-X-XX-+-"}
        "post chorus 1 (×16)"
        "same as chorus 1"
        "chorus 2 (×8)"
        "same as chorus 1"
        "post chorus 2 (×8)"
        "same as chorus 1"
        "pre bridge (×1)"
        (clojure.string/join (repeat 24 "X-- "))
        "bridge (×2)"
        (clojure.string/join (repeat 8 "X--+--X-- "))
        "outro (ad lib)"
        (str "+--+--X-- "
             (clojure.string/join (repeat 5 "X--+--X-- "))
             "X--+--XOXO")
        ]}]

      ["The Exquisite Machinery Of Torture"
       {:notes-per-beat 6
        :patterns
        ["intro (×4, the last without crash washing)"
         (str "+--+-X-+-+-- "
              "+-X-+-+-- "
              "+-X-+-+-- "
              "+-X-+-+-- "
              "+-X-+-+-- ")
         "verse 1 (×2)"
         (str (clojure.string/join (repeat 13 "X--XX-- ")) "X--XO")
         "chorus 1 (×1)"
         (str (clojure.string/join (repeat 13 "+X-+-X- ")) "+X-XO")
         "transition (×1)"
         "X--XX--X-+--"
         "verse 2 (×2)"
         "same as verse 1"
         "chorus 2 (×2)"
         (str (clojure.string/join (repeat 6 "+X-+-X- ")) "+X-+X-")
         "guitar solo (×3)"
         (str "X--XOX-X-X-X-X-X-XX-X-X- "
              "X--XOX-X-X-X-X-X-XX-X-XO ")
         "post guitar solo (×4)"
         (str "X--XOX--X-- "
              "X--XOX--X-- "
              "X--XOX--X-- "
              "X--XOX--X-- "
              "X-+- ")
         "transition 2 (×1)"
         (str "X--XX-- "
              "X--XX-- "
              "X--XX-- "
              "+--")
         "verse 3"
         "same as verse 1"
         "final chorus (×1)"
         "same as chorus 2 + chorus 1"
         "end (×1)"
         "X--XX--X"
         ]}]

      ["Elastic"
       {:patterns
        ["intro (×4)"
         (str "X--+- X-- XOX--X--X- "
              "X-+- XOX--X--X- "
              "X-+- XOX--X--X- "
              "X-+- XOX--X--X- "
              "X-XO")
         "transition"
         "XOOX- XOOX- XOXOX- XOOX- XOOX- XOOX- -"
         "pre verse 1 (×2)"
         (str "XOOX- XOOX- XOXOX- "
              "XOOX- XOOX- XOOX- XOXOX- "
              "XOOX- XOOX- XOOX- XOXOX- "
              "XOOX-"
              "XOOX- XOOX- XOXOX- "
              "XOOX- XOOX- XOOX- XOXOX- "
              "XOOX- XOOX- XOOX- XOXOX- "
              "XOOX- X-")
         "verse 1 (×4)"
         "same as pre verse 1"
         "post verse 1 (×2)"
         "same as intro"
         "breakdown (×1), based on the « post breakdown » below"
         (str "X--+-- +-+-+- "
              "X--X--+-- +-+-+-- "
              "X--+-- +-+-+- "
              "X--X--X--+-- +-+-+-+--X-- "
              "X--+-- +-+-+- "
              "X--X--+-- +-+-+-- "
              "X--+-- +-+-+- "
              "X--X--X--+-- +-+-+-X--XXO ")
         "post breakdown (×4)"
         (str "X--X-- X-X-X- "
              "X--X--X-- X-X-X-- "
              "X--X-- X-X-X- "
              "X--X--X--X-- X-X-X-X--X-- ")
         "pre guitar solo (×4)"
         (str "XOOX--X--X-XX-X-X-- "
              "XOOX--X--X-XX-X-X-- "
              "XOOX--X--X-XX-X-X-- "
              "XOOX-+O")
         "guitar solo (×4)"
         "same as pre guitar solo"
         "transition"
         "same as transition + one beat"
         "verse 2 (×4)"
         "same as pre verse 1"
         "post verse 2 (×4, half instrumental, half sang)"
         "same as intro"
         "outro"
         (str "X-+-+-+- X-+-+- X-+-+- X-+-+- "
              "X-+-+-+- X-+-+- X-+-+- X-+-+- "
              "X-+-+-+- X-+-+- X-+-+- X-+-+- "
              "X-+-+-+- X-+-+- X-+-+- X-+-+- "
              "X-+-+-+- X-+-+- X-+-+- X-+- ")
         ]}]]]

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
