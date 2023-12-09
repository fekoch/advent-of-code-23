(ns day03
  (:require [clojure.string :as str]
            [clojure.core.matrix :refer :all]))

(def ifile (clojure.java.io/resource (str "day03/" "test1")))
;(def ifile (clojure.java.io/resource (str "day03/" "input")))

; Wann ist ein digit relevant?
;   Wenn es neben einem Char ist, oder einem relevanten digit

; =>  find Char then search surroundings
(def file-str
  (slurp ifile)
  )

(def file-matrix (vec (map vec (str/split-lines file-str)))
  )

(println file-str)
(println (str "Shape: " (shape file-matrix)))

(defn get
  "Get the value at index (specified by a vector)"
  [matrix index]
  (select matrix (index 0) (index 1))
  )
(for [index (index-seq file-matrix)]
  (do
    (println (str "i: " index))
    (println (str "v: " (get file-matrix index)))
    )
  )
