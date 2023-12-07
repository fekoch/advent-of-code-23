(ns day02
  (:require [clojure.string :as str]))

(def datadir "day02")
(def file "data")
(def ifile (str datadir "/" file))


(defn draw-fits-in [draw max-draw]
  "Test if draw fits into max-draw"
  (every? true?
          (for [k (keys draw)]
            (<=
              (draw k)
              (max-draw k)
              ))))


(defn game-possible? [draws max-draw]
  (every? (fn [draw]
            (draw-fits-in draw max-draw))
          draws))



; draws: "1 red, 2 blue; 2 red, 1 blue"
; draw: "1 red, 2 blue"

(defn match-draw [draw-str]
  (re-seq #"(\d+) (\w+),?" draw-str))


; matched-draw-seq (["1 red," "1" "red"] [...] ... )

(defn parse-draw [matched-draw-seq]
  (into (sorted-map)
        (mapv #(vector (% 2) (Integer/parseInt (% 1)))
              matched-draw-seq)))




(defn right-side [line] (last (str/split line #":")))

(defn match-draws [line]
  (letfn [
          (get-draw-str-seq [line] (str/split (right-side line) #";"))
          (str-to-draw [draw-str] (parse-draw (match-draw draw-str)))]

    (mapv str-to-draw (get-draw-str-seq line))))



(defn parse-game [line]
  (let [id (re-find #"(?<=Game )\d+" line)
        draws (match-draws line)]

    [(Integer/parseInt id) draws]))

(defn fewest-cubes
  "Find the fewest number of cubes of each color for a sequence of draws"
  [draw-seq]
  (let [colors '("red" "green" "blue")]
    (zipmap
      colors
      (for [color colors]
        (let [count-seq (map #(get % color -1) draw-seq)]
          (apply max count-seq)
          )
        )
      )
    )
  )



(def max-draw {"red" 12 "green" 13 "blue" 14})
(with-open [xin (clojure.java.io/reader (clojure.java.io/resource ifile))]
  (println
    (str "sum of valid ids: "
         (transduce (comp
                      (map parse-game)
                      (filter #(game-possible? (second %) max-draw))
                      (map first))
                    +
                    (line-seq xin)
                    )
         ))
  )




