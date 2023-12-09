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

(defn calib-value [^String s]
  "return the calibration value for s"
  (Integer/parseInt
    (let [dgs (only-digits s)]
      (str
        (first dgs)
        (last dgs)
        ))
    )
  )


(defn part-one []
  "Part one of day one"
  (with-open
    [xin (clojure.java.io/reader (clojure.java.io/resource ifile))]
    (reduce +
            (map calib-value
                 (line-seq xin)
                 )
            )
    )
  )

(println (str "Part One: " (part-one)))
