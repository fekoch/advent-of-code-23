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
  select (matrix (index 0) (index 1))
  )

(defn is-symbol?
  [^Character c]
  (and
    (not (= c \.))
    (not (Character/isDigit c))
    ))
(defn is-digit?
  [index]
  (Character/isDigit (get file-matrix index))
  )

(defn get-symbol-keys []
  (filter #(is-symbol? (get file-matrix %)) (index-seq file-matrix)))

(defn get-sur-digit-keys [key excluded-keys]
  (let [excluded-keys (conj excluded-keys key)
        ; all wrong : Zahlen sind nur in einer Zeile
        offsets (for [x [-1 0 1] y [-1 0 1]] [x y])
        all-keys (mapv #(mapv + %1 %2) offsets (repeat key))
        check-keys (filter #(not (contains? excluded-keys %)) all-keys)
        ]
    (filter is-digit? check-keys)
    )
  )

(defn f [start-keys]
  (loop [start-keys start-keys excluded-keys []]
    (let [new-keys (get-sur-digit-keys )])
    )
  )

;(for [index (index-seq file-matrix)]
;  (do
;    (println (str "i: " index))
;    (println (str "v: " (get file-matrix index)))
;    )
;  )
