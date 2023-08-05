(ns pattashuggah.db)

(defn str-repeat [times repeated]
   (clojure.string/join (repeat times repeated)))

(defn str-join [pre [times repeated] post]
   (str pre (str-repeat times repeated) post))

(defn join-first [n txt]
   (clojure.string/join (take n txt)))

(defn join-last [n txt]
   (clojure.string/join (take-last n txt)))

(defn join-middle [start n txt]
   (clojure.string/join (join-first n (join-last (- (count txt) start) txt))))

(defn next-power-of-2
    ([n start] (if (>= start n) start (next-power-of-2 n (* 2 start)))))

(defn pad-with-dots [txt start]
  (let [l (count (clojure.string/join (remove clojure.string/blank? txt)))
        n (- (next-power-of-2 l start) l)
        pad (clojure.string/join "" (repeat n "."))]
    (str txt " " pad)))

(defn siempre-factory [pattern]
  (fn [pre reps post]  ; pre:  how many chars from the end to begin with
                       ; reps: how many repetitions
                       ; post: how many chars from the beginning to end with
    (str-join (if (= 0 pre)
              ""
              (join-last (+ pre 1) pattern))
            [reps pattern]
            (join-first post pattern))))


(def GHSIM_pattern "X-+-X-X-+O+-X-+O+-X-+O+-X-+-X-X-+-X-X-+O+-X-X-")
(def GHSIM_ (str GHSIM_pattern " "))
(def GHSIM (siempre-factory GHSIM_))

(def default-db
  {:disco
   [["Contradictions Collapse" "1991"
     ["Paralyzing Ignorance"
      "Erroneous Manipulation"
      "Abnegating Cecity"
      "Internal Evidence"
      "Qualms Of Reality"
      "We'll Never See The Day"
      "Greed"
      "Choirs Of Devastation"
      "Cadaverous Mastication"]]

    ["None" "1994"
     [["Humiliative"
       {:patterns
        ["intro"
         (str-join "" [2 "XX--+-+-XX-XX-+-+-+-+- "] "XX--+-+-XX-XX-+-+-+- ")]}]

      "Sickening"
      "Ritual"
      "Gods Of Rapture"
      "Aztec Two-Step"]]

    ["Destroy Erase Improve" "1995"
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
          :pattern (str-join "" [9 "X-X-- "] "X--")}
         "verse 1 (×4)"
         "same as pre verse 1"
         "post verse  1 (×2)"
         {:notes-per-beat 3
          :pattern "XXOXOX-- XXOXOX-- XXOXOXOO XXOX-- XXOXOX- XXOXOXOXOXO"}
         "pre chorus 1 (×1)"
         (str-join "" [10 "X--+--+-- "] "X--+--")
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
          :pattern (str-join "" [9 "XOX-+ "] "X--")}
         "post verse 2 (×2)"
         "same as post verse 1"
         "intro again (×2)"
         "same as intro"
         "intro again stronger (×1)"
         (str-join "" [4 "X-X-XOX-- "] "")
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
         (let [nine-times-five (str-join "" [5 "X--+--+-- "] "")]
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
         (str-join "" [2 "X-X-+- X-X-+- X-X-+- X-X-X-+- "] "")
         "verse 1 (×2)"
         (str-join "" [4 "X-X-+- X-X-+- X-X-+- X-X-X-+- "] "")
         "post verse 1 (×1)"
         "same as verse 1"
         "verse 2, first time (×1)"
         "same as verse 1"
         "verse 2, second time (×1)"
         (str-join ""
                   [3 "X-X-+- X-X-+- X-X-+- X-X-X-+- "]
                   "X-X-+- X-X-+- X-X-+- ")
         "end of verse 2"
         "X-- X-- X-- X-- +-+-"
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
         "X-+X-X-X-+X-+ X-+X- X-+X-X-X-+X-+ -"
         "guitar solo part 1 (×3)"
         "same as pre guitar solo"
         "guitar solo part 1, last (×1)"
         "same as pre guitar solo"
         "guitar solo part 2 (×6)"
         (str "+ "       "X--X-+- X--X-+- X--+-+- X--X-+- "
              "X--X--X " "X-XX-+- "
              "X-- "     "X--X-+- XX-X--X--+-")
         "verse 4"
         "TODO"
         "post verse 4"
         "TODO"
         "pre bridge (×4)"
         "XX-XX- XOXOX-"
         "bridge, instrumental (×8)"
         "same as pre bridge"
         "bridge, sang (×8)"
         "same as pre bridge"
         "post bridge 1 (×1)"
         (str-join "" [16 "X-X-- "] "X-+-X-+-X-+-")
         "post bridge 2 (×1)"
         "same as post bridge 1"
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

    ["Chaosphere" "1998"
     [["Concatenation"
      {:BPM 146
       :patterns
       ["intro (×4)"
        (str-join "" [6 "X--XOX-X-- "] "X-+-")
        "verse 1 (×4)"
        (str-join "" [6 "X--XOX-X-+ "] "X-+-")
        "chorus 1 (×2)"
        "same as intro"
        "intro 2 (×2)"
        "same as intro"
        "verse 2 (×4)"
        (str "X--XOX-X-+ X--XOX-X-X-+ X--XOX-X-+ X--XOX-X-X-+ "
             "X--XOX-X-+ X--XOX-X-+")
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
      {:youtube "4A_tSyJBsRQ"
       :BPM 77
       :patterns
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
      {:comments (str "Although quite fast these patterns are only singles. "
                      "Drummers working on their hands could take this "
                      "opportunity to work their singles leaded by their "
                      "weak hand. Just sayin'.")
       :BPM
        [172 "intro"
          86 "pre guitar solo (1'17\")"]
       :patterns
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
        (str "+-+- "
             "X-X-+-+-+- " "X-X-+-+-+- " "X-X-+- "
             "X-X-+-+-+- " "X-X-+-+-+- " "X-X-+-+-+- " "X-X-+- "
             "X-X-+-+-+- " "X-X-+-+-+- " "X-X-+-+-+- " "X-X-+- "
             "X-X-+-+-+- " "X-X-+-+-+- " "X-X-+-")
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
       :comments "BPM varies quite often but never more than 2 ou 3 units."
       :BPM 103
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
        (str-join "" [10 "X-- "] "XO")
        "verse 1 (×4)"
        (str-join "" [20 "X-- "] "X-XO")
        "post verse 1 (×2)"
        (str (str-join "" [6 "X-- "] "XX-XOX- ")
             (str-join "" [7 "X-- "] "XX-XOX- ")
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
      {:BPM
        [ 76 "intro"
         152 "verses maybe if you want"]
       :patterns
       ["intro (×4)"
        "X-X-- X-X-- X-X-- X-X-X-- X-X-- X-X-- "
        "transition (×1)"
        "X-X-- X-X-- X-X-+-"
        "verse 1 (×1)"
        (str-join "" [25 "X-X-+ "] "X--")
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
        (str "XOX -OXOX -OXOXOXOX "
           "-OXOX -OXOX -OXOXOXOX "
           "-OXOX -OXOX -OXOXOXOX "
           "-OXOX -OXO ")
        "pre end (×4)"
        (str
          "X-XOOX--X-- "
          "X-+XOX--X-- "
          "X-XOOX--X-- "
          "X-+XOX--X-- "
          "X-XOOX--X-- "
          "X-+XOX-+- ")
        "end riff (until fade out)"
        (str-join "" [6 "X--X-XX-- "] "X--X-XX-+- ")
       ]}]

      ["Sane"
       {:BPM
        [ 68 "intro"
         136 "verses maybe if you want"]
       :patterns
       ["intro (×8)"
        "X-- X-- X-- X-- X-XO"
        "verse 1 (×4)"
        (str-join "X- " [9 "+XO "] "+X-")
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
        (str-join "" [24 "X-- "] "")
        "bridge (×2)"
        (str-join "" [8 "X--+--X-- "] "")
        "outro (ad lib)"
        (str-join "+--+--X-- " [5 "X--+--X-- "] "X--+--XOXO")
        ]}]

      ["The Exquisite Machinery Of Torture"
       {:notes-per-beat 6
        :BPM
        [ 64 "intro"
         136 "choruses maybe if you want"]
        :patterns
        ["intro (×4)"
         {:comments "The last without crash washing."
          :pattern (str-join "" [5 "+--+-X-+- "] "+--")}
         "verse 1 (×2)"
         (str-join "" [13 "X--XX-- "] "X--XO")
         "chorus 1 (×1)"
         (str-join "" [13 "+X-+-X- "] "+X-XO")
         "transition (×1)"
         "X--XX--X-+--"
         "verse 2 (×2)"
         "same as verse 1"
         "chorus 2 (×2)"
         (str-join "" [6 "+X-+-X- "] "+X-+X-")
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
       {:BPM 64
        :patterns
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
              "XOOX- "
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
              "X--X--X--+-- +-+-+- +--X-- "
              "X--+-- +-+-+- "
              "X--X--+-- +-+-+-- "
              "X--+-- +-+-+- "
              "X--X--X--+-- +-+-+- X--XXO ")
         "post breakdown (×4)"
         (str "X--X-- X-X-X- "
              "X--X--X-- X-X-X-- "
              "X--X-- X-X-X- "
              "X--X--X--X-- X-X-X- "
              "X--X-- ")
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

    ["Nothing" "2002"
     [["Stengah"
      {:comments "Some anacrusis make this song tedious to encode."
       :BPM
        [ 60 "intro"
         120 "guitar solo"
          60 "bridge"]
       :patterns
       ["intro with a single guitar (×1)"
        (str-join ""
                  [5 "X--X-+-X--X--X-+-X--+- "]
                  "X--X-+-X--X--X--+- ")
        "intro (×2)"
        "same as intro"
        "pre pre verse 1, the first (×1)"
        (str-join "X- " [6 "+- "] "-X")
        "pre pre verse 1 (×7)"
        (str-join "-- " [6 "+- "] "-X")
        "pre verse 1, the first (×1)"
        (str-join "-- " [4 "X-- "] "X-")
        "pre verse 1 (×6)"
        (str-join "+- " [4 "X-- "] "X-")
        "pre verse 1, the last (×1)"
        (str-join "+- " [2 "X-- "] "X-+-+--X")
        "verse 1 (×2)"
        (str-join "--X--X--X--X--X- "
                  [2 "--X--X--X--X--X- "]
                  "--X--X--X-XX-+-X")
        "post verse 1 (×4)"
        "same as preverse 1 but 4 repetitions instead of 8"
        "verse 1 again (×1)"
        (str-join "--X--X--X--X--X- "
                  [2 "--X--X--X--X--X- "]
                  "--X--X--X-+-+-+-")
        "chorus 1 (×2)"
        (str-join ""
                  [2 "X--X-+-X--X--X-+-X--+- "]
                  "X--X-+-X--X--X-+-X-- ")
        "post chorus 1 (×1)"
        (str "X--X-+-X--X--X-+-X--+- "
             "X--X-+-X--X--X-+-+X-+- +--X")
        "verse 2, post verse 2, verse 2 again, chorus 2 and post chorus 2"
        "same as 1"
        "pre guitar solo (×1)"
        (str "X--X--X-X- "
             "X--X--X-X- "
             "X--X--X-X- "
             "X--X--X-X-X-X- "
             "X--X--X-X- "
             "X--X--X-X- "
             ; second turn
             "X--X--X-X- "
             "X--X--X-X- "
             "X--X--X-X-X-X- "
             "X--X--X-X- "
             "X--X--X-X- "
             "X--X--X-X- "
             )
        "guitar solo (×4)"
        (str "X--X--X-X- "
             "X--X--X-X-X-X- "
             "X--X--X-X-X-X- "
             "X--X-- "
             "X--X--X-X- "
             "X--X--X-X- "
             )
        "post guitar solo (×2)"
        {:notes-per-beat 4
         :pattern (str-join "" [6 "X--X--X-X- "] "X-+-")}
        "pre bridge (×2)"
        (str-join "" [8 "X--X-+- "] "X--X--+-")
        "transition (×1)"
        (str-join "" [8 "X--X-+- "] "X-+-+-+-")
        "bridge (×2)"
        (str-join "" [18 "X--X-+- "] "XO")
        "outro (×4)"
        "same as intro but with the backbeat"
        "end (×1)"
        "X--X"
       ]}]

      ["Rational Gaze"
       {:BPM 66
       :patterns
       ["intro (×2)"
        {:comments "The second one without the second and two last hits."
         :pattern (str-join "" [5 "XX--+-XX--XX-X--XX--XX-+- "] "XX-")}
        "pre verse 1 (×2)"
        (str (str-join "" [3 "XX--+- "] "XX-XX-+- ")
             (str-join "" [2 "XX--+- "] "XX-XX-+- ")
             (str-join "" [3 "XX--+- "] "")
             )
        "verse 1, part 1 (×4)"
        "same as pre verse 1"
        "verse 1, part 2 (×2)"
        (str (str-join "" [1 "XX--+- "] "XX-XX-+- ")
             (str-join "" [2 "XX--+- "] "XX-XX-+- ")
             (str-join "" [5 "XX--+- "] "")
             )
        "verse 1, part 3 (×4)"
        "same as pre verse 1"
        "chorus 1 (×2)"
        "same as intro"
        "instrumental interlude (×2)"
        {:comments "Based on the intro."
         :pattern (str-join "" [5 "X+--+-+X--+X-X--X+--+X-+- "] "XX-")}
        "breakdown (×2)"
        {:comments "Second time with the drums and without the second hit."
         :pattern (str-join ""
                            [2 "XX--XOX-+-X-+-XX--X-+-X-+- "]
                            "XX--XOX-+-X- ")}
        "guitar solo"
        "TODO"
        "post guitar solo"
        {:comments "Based on the breakdown."
         :pattern "TODO"}
        "pre outro"
        "TODO"
        "outro (ad lib)"
        (str-join "" [2 "X--XX-X--XX--XX-+-XX--+-X "] "X--XX-X--XX--X")
        ]}]

      ["Perpetual Black Second"
       {:BPM 74
        :patterns
       ["intro (×2)"
        (str-join "" [9 "X--X--X-XX-X-- "] "X-")
        "pre verse (×2)"
        (str-join "X- " [7 "X--X--X--X--X-X-X- "] "")
        "verse 1 (×2)"
        (str-join "X- " [7 "X--X--X-X-X--X--X- "] "")
        "intro 2 (×1)"
        "same as intro"
        "verse 2 (×2)"
        "same as verse 1"
        "post verse (×2)"
        "same as pre verse"
        "pre guitar solo"
        (str "X-X-X-X-+- X-X+-- X-X-X-X-X- "
             "X-X-X-X-+- X-X+-- X-X-X-X-X-X- "
             "X-X-X-X-+- X-X+-- X-X-X-X-X- "
             "X-X-X-X-+- X-X+-- X-X-X-X-X-X- "
             "X-X-X-X-+- X-X+-- X-X- "
             )
        "guitar solo (×4)"
        (str-join "" [4 "X-X-+-X--X--+- "] "X-X-+-+-")
        "last verse, same pattern but longer the wait (×2)"
        (str-join "" [9 "X-X-+-X--X--+- "] "X-")
        "end"
        "TODO"
       ]}]

      ["Closed Eye Visuals"
       {:BPM 66.5
        :patterns
        ["intro (×4)"
        (str "+-+-+-+- X-+-+-X-+- "
             "+-+-+-+-+- X-+-+-X-+- "
             "+-+-+-+-+- X-+-+-X-+- +-+-+-")
         "pre verse 1 (×1)"
         (str-join "" [3 "X-X-X-X-X-X-+-X-+-+- "] "+-+-")
         "verse 1, first part (×4)"
         (str (str-join "" [3 "X-X-X-X-X-X-+-X-+-+- "] "X-X-X-+- ")
              "X-X-X-X-X-X-+-X-+-+- "
              "X-X-X-X-+-+-X-+-+-X-+- "
              "X-X-X-X-+-+-X- X-X- "
              )
        ]}]
      ["Glints Collide"
      {:BPM 66
       :patterns
       ["intro"
        (str "X-+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O X--+-X--X--X-O "
             "X--+-X-+-")
       ]}]

      ["Organic Shadows"
      {:BPM 65
       :patterns
       ["intro (×2)"
        (str "X--X--+-+-X-X--X--+-+-X-X- X--X-- ")
        "intro with drums (×2)"
        "same as intro"
        "verse 1 (×4)"
        (str-join "" [2 "X--X--+-+-X-X--X--+-+-X-X- "] "X--X--+-+-X-")
        "mid verse 1 (×2)"
        "same as verse 1"
        "verse 1, part 2 (×4)"
        "same as verse 1"
        "post verse 1 (×2)"
        (str "X--X--X-+-X- X--X--X-+-X-X- "
             "X--X--X-+-X- X--X--X-+-X-X- "
             "X--X--X-+-X- ")
        "pre verse 2"
        (str-join ""
                  [3 "X--X--+-+-X-+-X-X- X--X--+-+-X-+-X-X-+- "]
                  "X--X--+-+-X-+-")
        "verse 2"
        "same as verse 2"
        "pre guitar solo (2 beats intro)"
        "X-+--X-X--+-+-+- "
        "pre guitar solo, instrumental (×2)"
        (str-join "" [4 "X-+--X-X--+-+- "] "X-+--X--")
        "pre guitar solo (×2)"
        "same as instrumental"
        "guitar solo (×2)"
        (str-join "" [6 "X-+--X-X--+-+-X--X-+- "] "X-")
        "post guitar solo (×2)"
        "same as pre verse 2"
        "verse 3 (×1)"
        "same as pre verse 2"
        "pre outro (2 beats)"
        "X-+-+OX-+- X-+-+- "
        "outro (fade)"
        (str-join ""
                  [3 "X-+-+-+- X-X-+- X-+-X-X--X--+-+-+- X-X-+- "]
                  "X-+-+-+-+- X-+- ")]}]

      ["Straws Pulled At Random"
      {:BPM 70
       :patterns
       ["intro (×4)"
        (str "X-X-+X-X-X--+- X-X-+X-X-X--+- "
             "X-X-+X-X-X--+- X-X-+X-X-X--+- "
             "X-X-+X--")
        "pre verse 1"
        (str "X-+-+-+-+- X-X-X--+- X-X-+X-X-X--+- "
             "X-X-+X-X-X--+- X-X-+X-X-X--+- "
             "X-X-+X--")
       ]}]
      ["Spasm"
       {:youtube "vnoufA9ZaGY"
        :BPM 70}]
      ["Nebulous"
       {:BPM 49.5}]
      "Obsidian"]]

    ["Catch Thirtythree" "2005"
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
      "Sum"]] ; ypXdO9RRE5U

    ["obZen" "2008"
     [["Combustion"
      {:BPM
        [220 "intro"
         110 "post intro riff"
         220 "second intro before first verse"
         110 "outro riff (same as post intro)"]
       :comments (str "There are double time and half time sections "
                      "but we keep the half time notation for the whole song.")
       :notes-per-beat 4
       :patterns
       ["guitar intro (×2)"
        {:comments "This pattern starts between the two first notes of the guitar."
         :pattern (str-join "-O " [13 "+O"] " +-OX")}
        "intro"
        "same as guitar intro"
        ]}]

      ["Electric Red"
      {:youtube "3V2Ay0T9ZwU"
       :BPM 55
       :comments
         [:p "Deciphering made easy thanks to "
             [:a {:href "https://www.youtube.com/watch?v=_oWIhUsJ4ik"} "Yogev"]
             ". During live shows you would occasionaly see some of "
             "them headbanging at 110 (like during "
             [:a {:href "https://www.youtube.com/watch?v=c1cwyNwjLxU"}
                 "this one"] "), but I keep it at 55."]
       :patterns
       ["intro part 1 (×2)"
        (str-join "" [4 "X--X--+--+--+- "] "X--X--X-")
        "intro part 2"
        (str-join "" [8 "X-+-+-- "] "X-+-+-+-")
        "verse 1"
        "same as intro part 2 (×2)"
        "intro part 1 (×1)"
        "same as intro part 1, but once"
        "verse 1, part 2"
        "same as verse 1"
        "post-verse (×2)"
        {:comments "Second time with eights heard."
         :pattern (str "X-- X-+-X-+- X--X--X-- X-+-+-+- X--X--"  ; no space
                       "X-- X-+-X-+- X--X--X-- X-+-+-+-+-+ "
                       "X-- X-+-X-+- X--X--X-- X-+-+-+- X--X--"  ; no space
                       "X-- X-+-X-+- X--X--X-- X-+-+-+-+ "
                       )}
        "fury"
        (str-join "" [8 "X--X-- XOOX-X-XOX-X-XOOX-X-XOOX- "] "X--X-+XO")
        "fury (instrumental)"
        "same as fury"
        "calm (×2)"
        (str-join "" [3 (str "XOOX+X- XOX-X- XOX-X- "
                             "XOOX+X- XO+X- XOX-X- ")] "XOOX+X- XOX-X- XOX- ")
        "less calm + singing (×2)"
        "same as calm"
        "calm (×2)"
        "same as calm"
        "intro part 1 (×2)"
        "same as intro part 1"
        "outro (faded a bit before the end of the cycle)"
        {:comments [:p "Use a " [:code "-"] " at the beginning to loop it."]
         :pattern (str-join "+X-+- " [27 "X-X--X-+- "] "X-X--X-+ ")}]}]

      ["Bleed"
      {:youtube "qc98u-eGzlc"
       :BPM 57.5
       :comments (str "In this song all XX have an O in between. "
                      "Please note thas this is a « quick and dirty » version. "
                      "A more complete one is in the works.")
       :patterns
       ["intro (throughout)"
        "XX-"
        "verse 1 (throughout)"
        "same as intro"
        "transition (×1)"
        "XX-X-XX-"
        "verse 2 (throughout, except some pieces of verse 1)"
        "XX-X-"
        "pre chorus (throughout)"
        "XXO"
        "chorus 1 (×1)"
        {:comments "Play a crash on the 2nd 16th of the nine."
         :pattern (str-join ""
                            [5 "X-X-XXOX-X- XXOXXOX-XXO "]
                            "X-X-XXOX-X- XXOX-+-")}
        "this is the same chorus 1 but with another notation"
        {:comments [:span "Some " [:code "X"] "s "
                          "are replaced with 'a', 'b', 'c' or 'd' "
                          "to show the note changes. What a nice mess: "
                          "3 hits fit in the 22 notes pattern but they use "
                          "a four notes cycle of notes."]
         :pattern
         (let [x "a-a-XXOb-b- XXOXXOc-XXO d-d-XXOa-a- XXOXXOb-XXO c-c-XXOd-d-"]
           (str x " XXOXXOd-XXO " x " XXOX-+-"))}
        "chorus, instrumental (×1)"
        "same as chorus"
        "chorus 2 (×1)"
        "same as chorus"
        "pre verse 3 and verse 3 (throughout)"
        "XX-X-X- XX-X-X- XX-X- XX- XX-X-"
        "pre bridge (×1)"
        (str-join "" [6 "XX-XXO+- XX-XX-XXO+-- "] "XX-XXO+-")
        "instrumental bridge"
        (str "XX-X-- XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-X-- XX-XX-XX-X-- "
             "XX-XX-XX-X ")
        "clean part (×1)"
        {:comments (str "Beware: the odd beats have high notes and "
                        "the low notes fall on the even beats "
                        "(they play high E, low D♯, high C♯, low F♯, "
                        "high D♯, low G, high F♯, low E) so you may be "
                        "tricked thinking the low D♯ is on the «one».")
         :pattern (str-join "" [32 "X-+-+-+- "] "")}
        "guitar solo (×1)"
        (str
          (str-join "" [11 "XX-XX-XX-X-+- XX-XX-XX-XX-X-+- "] "")
                           "XX-XX-XX-X-+- XX-XX-XX-XX-XX-XX-X-+- "
          (str-join "" [ 3 "XX-XX-XX-X-+- XX-XX-XX-XX-X-+- "] "")
                           "XX-XX-XX-X-+- XX-XX-XX-XX-XX-XX-X-+- "
                           "XX-XX-XX-X-+- XX-XX-XX-XX-X-+- "
                           "XX-XX-X")
        "transition (×1, just one beat)"
        "+-+-+-+-"
        "verse 4"
        "same as preverse 3 and verse 3"
        "transition and outro (throughout)"
        "XXO"]}]

      ["Lethargica"
       {:youtube "FnGApmk1a1o"
        :BPM 52.5}]

      ["Obzen"
       {:youtube "MW9WeSpwWjE"
        :BPM 85
        :notes-per-beat 4
        :patterns
        ["intro"
         {:comments
          "Each and every X is doubled."
          :pattern
          (str
            "XO+O+OXOXOXO+O+O " "XO+O+OXOXOXO+O+O+O "
            "XO+O+OXOXOXO+O+O " "XO+O+OXOXOXO+O+O+O "
            "XO+O+OXOXOXO+O+O " "XO+O+OXOXOXO+O+O+O "
            "XO+O+OXOXOXO+O+O " "XO+O+OXOXO "
            )}]}]

      ["This Spiteful Snake"
       {:youtube "xb8SfmX4MDI"
        :BPM
          [ 65 "intro"
           130 "second part of the song (2'45\")"
            65 "last verse (3'44\")"]
        :patterns
        ["intro (×4)"
         {:comments
          "Additional guitars appear at the third repetition."
          :pattern
          (str-join "" [2 "X-X-XOX-+-+- X-X-X-XOX-+-+- "] "X-X-XOX-+-+- ")}]}]
      ["Pineal Gland Optics"
       {:youtube "rCBuNEfADuo"
        :BPM
          [190 "intro"
            95 "interlude (1'42\") but maybe before if you want"]
        :notes-per-beat 4
        :comments [:p "During the double time moments (like in the intro) "
                      "it's not really 4 notes per beat but 2. It may be "
                      "easier to follow with 4 notes per beat though."]
        :patterns
        ["intro (with singles)"
         (str-join "" [10 "XO+O+O+OX- XO+O+OX- X-+OX- "] "XO+O+O+OX- XO+O+O")
         "intro (alternate version with doubles)"
         (str-join "" [10 "XX-X-X-XX- XX-X-XX- X--XX- "] "XX-X-X-XX- XX-X-X")
         "outro"
         {:comments [:p [:code "D"] " is a double with your strong hand and "
                        [:code "^"] " is a brutal hit on the snare. " [:br]
                        "The song fades before any hint about the length of "
                        "the cycle. Interestingly though, there a hit on a "
                        "crash at the beginning of the sixth line."]
          :pattern
          (str-join "X- DO^OX- DO^O^OX- "
                    [6 "DO^OX- DO^O^O^OX- DO^OX- DO^OX- DO^O^OX- "]
                       "DO^OX- DO^O^O^OX- DO^OX- ..")}]}]
      ["Pravus"
      {:youtube "bsvbtqGn7z8"
       :BPM 66.5
       :comments
         [:div
           [:p "The last riff could be quarter time but during their live"
               "shows they stay at around 66."]
           [:p "Not many diddles here. Maybe it's time to work on your weak "
               "hand lead."]]
       :patterns
       ["intro"
        (str-join "" [5 "X-+-X-X-+- X-+-X-X-X-+- X-+-X-X-+- X-+-X-X-X-X-+- "]
                  "X-+-X-X-+- X-+-X-X-X-+- X-+-")
        "intro, one clean guitar"
        (str-join "" [2 "X-+-X-X-+- X-+-X-X-X-+- X-+-X-X-+- X-+-X-X-X-X-+- "]
                  "X-+-X-X-+- X-+-X-X-X-+- X-+-X-X- X-+-X-")
        "intro again"
        "same as intro"
        "verse 1"
        "same as intro"
        "pre verse"
        (str-join "+-X- " [6 "+-+-+-X-+-+-X-+-X- "] "+-+-+-X-+-+-X-+- ")
        "1 bar to fill"
        "X--X--X- X-XX-X-- X--X--X--X--X-+-"
        "verse 2"
        (str-join "" [11 "X-+-+-X-+-X-+-+-X-X-+- "] "X-+-+-X-+-X-+-")
        "1 bar to fill"
        "same as previously"
        "post verses (×2)"
        {:comments
           [:p "The two last " [:code "X"] "s of the first time make five with "
               "the three firsts of the second. It makes the two times contain "
               "two " [:code "A"] "s in a row, like this: "
               [:code "CBABABABAABABABABAD"] "."]
         :pattern
           (str-join "X-X-X-+-+-+- "
                     [4 "X-X-X-X-X-+- X-X-X-X-X-+-+-+- "]
                     "X-X-")}
        "pre-bridge"
        (str-join "XOXOXOXO+-+- "
                  [4 (str-join "" [6 "XOXO+-+- "] "XOXOXOXO+-+- ")]
                  "XOXO")
        "bridge"
        {:comments
          [:p "Thanks to "
              [:a {:href "https://www.youtube.com/watch?v=euSki63c-SQ"}
                  "this video"]
              " for the hint."]
         :pattern (let [p23 "XOXO+-+- XOXOXOXO+-+- "
              p2323235 (str-join "" [3 p23] "XOXOXOXOXOXOXOXOXOXO ")]
          (str-join "XOXO+-+- " [4 p2323235] (str p23 "XOXO")))}
        "pre-outro"
        {:comments [:p "Let ring on second half."]
         :pattern
          (str-join "" [8 "X-X-X-+-+-+- X-X-X-+-+-+- X-+- "]
                    "X-X-X-+-+-+- X-X- X-+-+-+-X-X-+-+-")}
        "outro"
        (str-join "X-X-+-+-+-+- " [8 "X-X-+-+-+-+- X-+-X-X-+-+-+-+- "]
                    "X-X-+-+-+-+- X-+-X-X .")
       ]}]
      ["Dancers To A Discordant System"
      {:youtube "a1zFJKPOnXg"
       :BPM 62.5
       :comments
         [:p "Deciphering made easy thanks to "
             [:a {:href "https://www.youtube.com/watch?v=LGB-7viJrv0"} "Yogev"]
             "."]
       :notes-per-beat 6
       :patterns
       ["intro riff with only one guitar"
        {:comments (str "This is (9+8+4) + (9+6) + (9+11+4) = "
                        "21 + 15 + 24 = 7×3 + 5×3 + 8×3 "
                        "(3 notes per eighth notes).")
         :pattern
         (str-join "" [3 (str "X-X-XOXOO X-X-XOXO X-X- "
                              "X-X-XOXOO X-XOXO "
                              "X-X-XOXOO X-X-XOXOXOO X-X- ")]
                   "X-X- XOXOO X--")}
        "intro break"
        "+--+--+--+--"
        "intro riff with everyone but Jens"
        "same as intro riff"
        "post intro break"
        "X-X- XOXOO X--"
        "verse 1"
        (str-join "" [6 "XOOXO X-X-X-X-O XOOXO X-X-X-X-X-O "] "XOOXO X-X-X--")
        "verse 1, second part"
        (str-join "" [13 "XOXOO X-X-X-X-O "] "XOXOO X-X-O")
        "verse 2"
        "same as verse 1"
        "verse 2, second part"
        (str-join "" [8 "XOXOOXO X-X- "] "XOXOXOX-")
        "verse 2, last part, half sung half instrumental"
        (str-join "" [17 "XOXOOXO X-X- "] "XOXO-")
        "chorus 1"
        (str-join "" [21 "X-XOO X-X- "] "X-O")
        "interlude"
        (str-join "" [13 "XOX- X-OX-O XOX- "] "XOX- X-OX-O")
        "??"
        (str-join "" [4 "XOX- XOXOOX- "] "----")
        "FB riff"
        (str-join "" [13 "XOX-XOXOXOXOX- "] "XOX-XOXOXO")
        ]}]]]

    ["Koloss" "2012"
     [["I Am Colossus"
       {:youtube "fTYXbFsWg-M"
        :BPM 60
        :notes-per-beat 6
        :patterns
        ["intro (a measure of two beats)"
         "X--X-- XO+-+-"
         "verse 1 (×2)"
         (str-join "X--+- X-+-X-+-X-+-X-+-X- "
                   [3 "X-+-X-+-X-+-X-+-X-+-X- "]
                   "X-+-X--")
         "post verse 1"
         "same as verse 1, but only once and with notes"
         "verse 2 (×2)"
         (str "same as verse 1 but with a limbo thing at the beginning "
              "(second strong hit is concealed) and surimposed "
              "with patterns when going from a line to the next (two notes)")
         "post verse 2"
         (str
           (str-join "X--+- X-+-X-+-X- "
                     [5 "X-+-X-+-X-+-X- "]
                     "X-+-X-+-X-+")
           (str-join "-X-+-X-+-X-+-X- "
                     [5 "X-+-X-+-X-+-X- "]
                     "X-+-X-+-X-+"))
         "transition (two beats)"
         "same as intro"
         "pre bridge"
         (str-join "" [6 "X--XO+-- "] "")
         "bridge"
         (str "X--+- "
              "+-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X- "
              "X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X- "
              "X-+-X-- "
              "X-+-+-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-+- "
              "X-+-+-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-X-+-+- "
              "X-+-")
         "post bridge"
         ;"same as post verse 2 but with notes"
         (str-join "X--+- "
                     [5 "X-+- X-X-+- X--+- "]
                     "X-+- X-X-+- X-+-X-")
         "transition 2 (two beats)"
         {:comments "Guitars play this but the drums play a fill."
          :pattern "X-+-X-+-X-+O "}
         "guitar solo"
         (str "same as post verse 2, surimposed with dozens of notes at line "
              "changes, and with a pre-transition on the last beat")
         "transition 3 (same as intro + two silent beats)"
         "X--X-- XO+-+- +--+--+--+--"
         "verse 3 (×1)"
         (str "same as verse 2: limbo + superimposing, but surimposing "
              "patterns have about 6, 2, 6 and 2 notes")
         "post verse 3"
         {:comments (str
            "First half uses patterns from the verses "
            "and second half uses patterns from the post verses. "
            "Is it fair?")
          :pattern
            (str
              (str-join "X--+- X-+-X-+-X-+-X-+-X- "
                        [3 "X-+-X-+-X-+-X-+-X-+-X- "]
                        "X-+-X-+")
              (str-join "-X-+-X-+-X-+-X- "
                        [4 "X-+-X-+-X-+-X- "]
                        "X-+-X-+-X-+-X-+-X-+-X-+-X"))}]}]

      ["The Demon's Name Is Surveillance"
       {:youtube "YupejUfzRSg"
        :notes-per-beat 9
        :BPM 72
        :comments [:p "Every " [:code "X--++-"]
                      " could be a " [:code "X-+-+-"] "."]
        :patterns
        ["intro"
         {:comments
          "Last four strong hits are the melodic transition between lines."
          :pattern
          (let [p1 "X--++-"
                p2 (str "X--" p1)
                p3 (str "X--X--" p1)
                p123 (str p1 p2 p3 " ")
                p231 (str p2 p3 p1 " ")
                p312 (str p3 p1 p2 " ")
                transition "X-+O+-X-+O+- "]
            (str
              (str-join "" [2 p123] (str p1 " "    transition))
              (str-join "" [2 p231] (str "X--X-- " transition))
              (str-join "" [2 p312] (str "X--X-- " transition))
              (str-join "" [2 p123] (str p1 " "    transition))))}
         "verse 1"
         (let [short-p1 "X--++-+-- "
               p1 "X--++-+--++- "
               p2 (str "X--++-" p1)
               p3 (str "X--++-X--++-" p1)
               p123 (str p1 p2 p3)]
           (str-join (str short-p1 p2 p3)
                     [4 p123]
                     (str p1 "X--++-X--")))
         "the rest"
         "TODO"
         ]}]

      ["Do Not Look Down"
       {:youtube "CUuXiAsV7BQ"
        ; 223226 ? TODO
        :BPM 80}]
      ["Behind The Sun"
       {:youtube "f3JNm48a6u0"
        :BPM
          [47.5 "intro"
           95   "2'48\""
           47.5 "3'29\")"
           95   "4'15\""
           47.5 "4'34\")"
           95   "5'28\" or before if you want"
           47.5 "5'49\")"]}]
      ["The Hurt That Finds You First"
       {:youtube "edw4qyZg9-E"
        :BPM
          [250 "intro"
            63 "2'24\""]}]
      ["Marrow"
       {:youtube "7CH-AjajRCg"
        :BPM 150}]
      ["Break Those Bones Whose Sinews Gave It Motion"
       {:youtube "m9LpMZuBEMk"
        :BPM 68.5}]
      ["Swarm"
       {:youtube "QWh0796XQWE"
        :BPM 67.5}]
      ["Demiurge"
       {:youtube "zg2076b5Lqc"
        :BPM
          [85 "intro"
           42.5 "2'26\""
           85 "4'00\""
           42.5 "pre outro (4'18\")"]}]
      "The Last Vigil"]]

    ["The Violent Sleep Of Reason" "2016"
     [["Clockworks"
      {:youtube "axGn6qeJHcM"
       :comments [:span
                  "Special thanks to "
                  [:a {:target "_blank"
                       :href "https://www.youtube.com/watch?v=YwrSvpjdK-w"}
                      "Yogev"] ". "
                  "In this song " [:code "Tr"]
                  " is meant to be played as a " [:code "XXO"] " or a "
                  [:code "XOO"]
                  " but in a triplet to fit in the duration of two notes."]
       :patterns
       ["intro (×2)"
        (str-join ""
                  [3 "X--X--Tr X--X--X--X- X--X-- X--X--X-X-- "]
                     "X--X--Tr X--X--X--X--")
        "intro with lead guitar (×2)"
        "same as intro"
        "verse 1 (×1)"
        (str-join ""
                  [5 "X--X--Tr X--X--TrTr X--X--X--X--TrTrTr X--X--TrTr "]
                     "X--X--Tr X--X--TrTr X--X--X-")
        "post-verse 1 (×1)"
        {:comments [:span "Same numbers than verse 1 but with a different "
                          "orchestration (" [:code "Tr"] " are now "
                          [:code "X-"] ") and Jens only sings on the last "
                          "half of the pattern."]
         :pattern
        (str-join ""
                  [5 "X--X--X- X--X--X-X- X--X--X--X--X-X-X- X--X--X-X- "]
                     "X--X--X- X--X--X-X- X--X--X-")}
        "transition (×1)"
        "X-+- +-+- +-+- +-+-"
        "verse 2 (×1)"
        "same as verse 1"
        "chorus 1 (×2)"
        (str-join "" [3 "X--X--X--X--X--X-- TrTrTrTrTrTrTrTr "]
                        "X--X--X--X--X--X-- TrTrTrTr")
        "guitar solo (throughout the 16 bars)"
        {:notes-per-beat 4
         :pattern "X--"}
        "end of guitar solo, first part (×1)"
        "same as intro"
        "end of guitar solo, second and last part (×1)"
        {:comments [:span "The " [:code "XO"] "s at the end answer the guitar."]
         :pattern
        (str-join ""
                  [2 "X--X--Tr X--X--X--X- X--X-- X--X--X-X-- "]
           (str-join "X--X--Tr X--X--X--X- X--X- " [8 "+-XO "] ""))}
        "pre-verse 3"
        (str-join "" [20 "+-XO+- "] "+-+-+-+-")
        "verse 3"
        (let [threes (str-join "" [5 "XO+O+- "] "")
              twos   (str-join "" [4 "XO+- "] "")]
          (str threes twos
               threes twos "XO+- "
               threes twos "XO+- XO+- "
               threes twos "XO+- XO+- XO+- "
               threes (str-join "" [3 "XO+O+- "] "")))
       ]}]

      ["Born In Dissonance"
      {:youtube "xtO3VCu5wv4"
       :patterns
       ["intro (×1)"
        {:comments [:p "When this section loops (here with verse 1) "
                       "you get three " [:code "X-X--XX-"] " in a row."]
         :pattern (str-join ""
             [4 (str "X--XX- X-X--XX- X-X-X--XX- "
                     "X-X-X-X--XX- X-X-X-X--XX- X-X-X-X--XX- ")]
             "X--XX- X-X--XX- X-")}
        "verse 1 (×1)"
        "same as intro with different notes"
        "post verse 1 (×1)"
        (str "X--XX- X--XX- X--XX- X--XX- X--XX- X--XX- XOXO "
             "X--XX- X--XX- X--XX- X--XX- X--XX- X--XX- XOXO "
             "X--XX- X--XX- X--XX- X--XX- X--XX- X--XX- XOXO "
             "X--XX- X-")
        "verse 2 (×2)"
        {:comments [:p "Half of verse 1. "
                       "Now you get two " [:code "X-X--XX-"] " in a row."]
         :pattern (str-join ""
             [2 (str "X--XX- X-X--XX- X-X-X--XX- "
                     "X-X-X-X--XX- X-X-X-X--XX- X-X-X-X--XX- ")]
             "X--XX- X-")}
        "post verse 2 (×1)"
        {:comments [:div
                   [:p "Jens arrives after the 4th measure (3rd line) but "
                       "the pattern sequences continues unchanged. "
                       "Then we have 8 measures before the bridge. "
                       "That's why we have 12 measures (of 32 32nd notes, "
                       "hence the 384 total count)."]
                   [:p "Beware! The pattern sequence changes at beat 5 "
                       "in the 3rd line where you play a triple B-pattern, "
                       "maybe to land on beat 9 of the 4th line with XOX as "
                       "we did on beat 9 of the 2nd line."]
                   [:p "Then it changes again on beat F of this 4th line "
                       "for a nice mess."]]
         :pattern
        (str "X--XX- X--XX- X--XX- X--XX- XOX--XX- XOX--XX- "
             "X--XX- X--XX- X--XX- X--XX- XOX--XX- XOX--XX- "
             "X--XX- X--XX- X--XX- X--XX- XOX--XX- XOX--XX- "
             "X--XX- X--XX- X--XX- X--XX- XOX--XX- XOX--XX- "
             ; starts on beat 5 in the 3rd line
             "XOX--XX- "
             "X--XX- X--XX- X--XX- X--XX- XOX--XX- XOX--XX- "
             "X--XX- X--XX- X--XX- X--XX- XOX--XX- "
             ; starts on beat F in the 4th line
             "X--XX- XOX--XX- "
             "X--XX- XOX--XX- "
             "X--XX- X--XX- XOX--XX- "
             "X--XX- XOX--XX- "
             "X--XX- X--XX- XOX--XX- "
             "X--XX- X--XX- X--XX- XOX--XX- XOX--XX- "
             "X--XX- X--XX- X--XX- X--XX- X-+-")}
        "bridge"
        (str
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
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
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
             "X--XX-X--XX- " "X--XX-X--XX- "
             "X-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- "
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
      ["Violent Sleep Of Reason"
       {:notes-per-beat 3
        :patterns
       ["intro"
        "?"
        "intro part 2"
        ""
        "verse 1"
        (pad-with-dots (str
                 "X- " "XOX-XOX-X-- XOX-XOX-X-X- XX-XX-X- XX-XX-X- X-X-X-- "
          "XX-XX-X-- " "XOX-XOX-X-- XOX-XOX-X-X- XX-XX-X- XX-XX-X- X-X-X-- "
          "XX-XX-X-- " "XOX-XOX-X-- XOX-XOX-X-X- XX-XX-X- XX-XX-X- X-X-X-- "
          "XX-XX-X-- " "XOX-XOX-X-- XOX-XOX-X-X- XO") 3)
        "post-verse 1"
        "same as verse 1"
        "verse 2"
        "same as verse 1"
        "guitar solo"
        (str-join "" [3 (str "XXOX- "
                             "X-X-XX- "
                             "XXOX- "
                             "XXOX- "
                             "XXOX- "
                             "XXOX- "
                             "XXOX- "
                             "X-X-X-XX- "
                             "X-X-XX- ")]
                        (str "XXOX- "
                             "X-X-XX- "
                             "XXOX- "
                             "XXOX- "
                             "XXOX- "
                             "XXOXXO "))
        "post guitar solo"
        "same as guitar solo"
        "bridge"
        {:notes-per-beat 6
         :pattern (str-join "X-+-XX-X-XX-X- "
                            [5 "X--+-XX-X-XX-X- "]
                            "X--+-+- ")}
        "bridge with vocals (×2)"
        "same as bridge"
        "pre verse 3"
        {:notes-per-beat 6
         :comments "Same as verse but half time."
         :pattern (pad-with-dots (str
                 "X- " "XOX-XOX-X-- XOX-XOX-X-X- XX-XX-X- XX-XX-X- X-X-X-- "
          "XX-XX-X-- " "XOX-XOX-X-- XOX-XOX-X-X- XX-XX-X- XX-XX-X- X-X-X-- "
          "XX-XX-X-- " "XOX-XOX-X-- XOX-XOX-X-X- XX-XX-X- XX-XX-X- X-X-X-- "
          "XX-XX-X-- " "XOX-XOX-X-- XOX-XOX-X-X- XO") 3)}
        "verse 3"
        "same as verse but half time"
        "post verse 3"
        "same as verse but half time"
        "outro"
        "?"
        ]}]
      ; TODO Somewhere
      ; 55 + 55 + 55 + 27 = 192 = 16 × 12
      ; END
      ; 5×(X-XX--) + 4×(XX--)
      ; ---------- + 5
      ; ---------- + 6
      ; ---------- + 7        168
      ; 4×(X-XX--)
      ; 4×(X-XX--)             88 -> 256
      ["Ivory Tower"
      {:notes-per-beat 6
       :patterns
       ["intro"
        "XXX+ XXXX+ XXXXX+ XXX+ X+ XX+"
        "verse 1"
        (str "XX+ XXX+X+ XX+ XX+ XXX+X+ "
             "XX+ XXX+X+ XX+ XX+ XX+ XXX+X+ "
             "XX+ XXX+X+ XX+ XX+ XXX+X+ "
             "XX+ XXX+X+ XX+ XX+ XX+ XXX+X+ "
             "XX+ X++")
        "inter verse"
        "same as intro"
        "verse 2"
        "same as verse 1"]}]
      "Stifled"
      "Nostrum"
      "Our Rage Won't Die"
      "Into Decay"]]

    ["Immutable" "2022"
     [["Broken Cog"
       {:BPM 58
        :notes-per-beat 6
        :patterns
        ["intro"
         (str-join "" [8 "XooXo+- XooXoXooXooXo+-"] "XooXo+- XooXooXoo")]}]
     ["The Abysmal Eye"
      {:BPM
       [88 "intro"
        44 "final riff (4'07\")"]
       :comments
        [:div
        "This one is difficult to encode neatly because the pattern is very "
        "long. Again, thanks to Yogev for his work ("
        [:a {:href "https://www.youtube.com/watch?v=VGHMFuNAHZQ"}
            "video with the full song"]
        " - "
        [:a {:href "https://www.youtube.com/watch?v=a1RwXRzcv_4"}
            "detailed explanation"]
        "). I'm gonna use a variation of his notation:"
        [:ul
         [:li [:code "T"] " means " [:code "XoXo"]]
         [:li [:code "D"] " means " [:code "X-+-"]]
         [:li [:code "v"] " means " [:code "+-+-"]]]]
       :patterns
       ["intro"
        {:notes-per-beat 2
         :comments
         [:div
           [:p "Some additional guitars on second half."]
           [:p "The repeating pattern (×4 + 1 maimed) is "
               [:code "TTTD TDv TTDv TD TTDv TTD TD TTTD"] "."]]
          :pattern (str-join "" [4 "TTTD TDv TTDv TD TTDv TTD TD TTTD "]
                                   "TTTD TDv TTDv TD TTDv TTD TD TT")}]}]
     ["Light The Shortening Fuse"
      {:BPM
       [100 "intro"
         50 "chorus (1'16\")"]}]
     ["Phantoms"
      {:BPM
       [ 54 "intro"
        108 "outro riff (2'52\")"]
       :notes-per-beat 4
       :patterns
       ["pre-outro"
        (str-join "" [2 "X-+O+-+-+O+-+O+-+-+O+-+O+-X-+- "] "+-+-")
        "outro (three times)"
        {:comments
         "The pre-outro pattern starts on the second beat! And also line 3."
         :pattern (str-join "X-+- "
                            [8 "X-+O+-+-+O+-+O+-+-+O+-+O+-X-+- "]
                               "X-+O+-+-+O+-")}
       ]}]
     ["Ligature Marks"
      {:BPM 74}]

     ["God He Sees In Mirrors"
      {:comments
        [:div
          [:p "We have the same 46 32nds pattern throughout the song "
              "(the A pattern of the intro). I personaly would have started "
              "it on the third X but let's make it simple."]
          [:p "I decided to encode it as singles for clarity "
              "but you could play it like this: "
              [:code (clojure.string/replace GHSIM_pattern "+O+" "-X-")]
              "."]]
       :BPM
       [74 "intro"
        37 "guitar solo (2'58\")"
        74 "guitar solo (second part 3'24\")"]
       :patterns
       ["intro"
        (GHSIM 0 5 26)
        "pre verse 1 (one silent bar but the pattern still goes on)"
        (GHSIM 20 0 12)
        "verse 1"
        (GHSIM 34 2 2)
        "mid verse 1 (one silent bar but the pattern still goes on)"
        (join-middle 2 32 GHSIM_)
        "verse 1, part 2"
        (GHSIM 12 2 24)
        "chorus 1"
        (GHSIM 22 2 14)
        "post chorus 1"
        (GHSIM 32 0 32)
        "post chorus 1, part 2"
        (GHSIM 14 2 22)
        "verse 2"
        (GHSIM 24 5 2)
        "verse 2, part 2"
        (GHSIM 44 1 38)
        "post verse 2"
        (GHSIM 8 2 28)
        "pre verse 3 (six beats in a bar)"
        (GHSIM 18 0 30)
        "verse 3"
        (GHSIM 16 5 10)
        "guitar solo (quarter time)"
        (GHSIM 36 4 36)
        "guitar solo, part 2 (half time)"
        (GHSIM 10 5 16)
        "pre verse 4 (one bar)"
        (GHSIM 30 0 2)
        "verse 4 (starts like verse 2, part 2)"
        (GHSIM 44 3 10)
        "mid verse 4 (six beats in a bar)"
        (GHSIM 36 0 12)
        "verse 4, part 2 (same as verse 1)"
        (GHSIM 34 2 2)
        "2nd mid verse 4 (one bar, same as mid verse 1)"
        (join-middle 2 32 GHSIM_)
        "verse 4, part 3 (same as verse 1, part 2)"
        (GHSIM 12 2 24)
        "chorus 2 (same as chorus 1)"
        (GHSIM 22 2 14)]}]

     ["They Move Below"
      {:BPM
       [79 "drums in"
        40 "longer riff (5'45\")"]}]
     ["Kaleidoscope"
      {:BPM
       [74 "intro"
        37 "bridge towards the end (3'08\")"
        74 "final riff (3'36\")"]}]
     "Black Cathedral"
     ["I Am That Thirst"
      {:BPM 70}]
     ["The Faultless"
      {:BPM 66}]
     ["Armies Of The Preposterous"
      {:BPM 66}]
     "Past Tense"
     ]]
     ]})
