(ns day01)

(def datadir "day01_data")
(def file "input")
(def ifile (str datadir "/" file))


(let [zero (int \0)
      nine (int \9)]                                        ;; define those constants outside to only compute once
  (defn is-digit [^Character c]
    (<= zero (int c) nine)))

(defn only-digits
  "return only the 'digit' chars"
  [^String s]
  (filter is-digit (seq s))
  )

(defn calib-value [^String s digit-conversion-function]
  "return the calibration value for s"
  (Integer/parseInt
    (let [dgs (digit-conversion-function s)]
      (str
        (first dgs)
        (last dgs)
        ))
    )
  )

(def digit-map {
                "one"   1
                "two"   2
                "three" 3
                "four"  4
                "five"  5
                "six"   6
                "seven" 7
                "eight" 8
                "nine"  9
                }
  )

(def reversed-digit-map {
                         "xis"   6
                         "eerht" 3
                         "owt"   2
                         "neves" 7
                         "evif"  5
                         "thgie" 8
                         "eno"   1
                         "enin"  9
                         "ruof"  4
                         }
  )


(defn part-one []
  "Part one of day one"
  (with-open
    [xin (clojure.java.io/reader (clojure.java.io/resource ifile))]
    (reduce +
            (map #(calib-value % only-digits)
                 (line-seq xin)
                 )
            )
    )
  )

(defn int-ify-first-substring [line digit-map]
  (let [pattern (re-pattern (str "\\d|" (clojure.string/join "|" (keys digit-map))))]
    (let [match (re-find pattern line)]
      (if (= (count match) 1)
        (Integer/parseInt match)
        (digit-map match)
        )
      )
    )
  )

(defn part-two []
  "Part two of day one"
  (with-open
    [xin (clojure.java.io/reader (clojure.java.io/resource ifile))]
    (reduce +
            (for [line (line-seq xin)]
              (Integer/parseInt
                (str
                  (int-ify-first-substring line digit-map)
                  (int-ify-first-substring (clojure.string/reverse line) reversed-digit-map)
                  )
                )
              )
            )
    )
  )

;(println (str "Part One: " (part-one)))
(println (str "Part Two: " (part-two)))
