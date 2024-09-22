(ns day03
  (:require [clojure.string :as str])
  (:use [clojure.core.matrix]))

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
  ((matrix (index 1)) (index 0))
  )

(defn symbol?
  [^Character c]
  (and
    (not (= c \.))
    (not (Character/isDigit c))
    ))

(defn mask-index
  [index]
  (for [dx [-1 0 1] dy [-1 0 1]] [(- (index 0) dx) (- (index 1) dy)])
  )

(defn mark-adjecent-numbers
  [x y]
  (let* [matrix file-matrix
         curr-char (get matrix [x y])]
    (cond
      (symbol? curr-char) (mask-index [x y])
      :else #{})
    )
  )

(loop [
       s #{}
       indices (for [x (range ((shape file-matrix) 0)) y (range ((shape file-matrix) 1))] [x y])
       ]
  (let* [
         curr-idx (peek indices)
         x (curr-idx 0)
         y (curr-idx 1)
         marks (mark-adjecent-numbers x y)
         new-set (apply conj s marks)]
    (recur new-set (pop indices))
    ))
