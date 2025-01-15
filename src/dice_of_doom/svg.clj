(ns dice-of-doom.svg
  (:require [clojure.string :as str]
            [clojure.test :refer :all]))

(defn print-tag [tag-name atts closingp]
  (str "<" (if closingp "/" "") (str/lower-case tag-name)
       (apply str (map (fn [[k v]]
                         (str " " (name k) "=\"" v "\""))
                       atts))
       ">"))

(defmacro tag [name atts & body]
  `(str
    (print-tag '~name
               (map (fn [[k# v#]]
                      [(name k#) v#])
                    (partition 2 ~atts))
               nil)
    (str ~@body)
    (print-tag ~name nil true)))


(defmacro html [& body]
  `(tag "html" []
        ~@body))

(defmacro body [& body]
  `(tag "body" []
        ~@body))

(defmacro svg [width height & body]
  `(tag "svg"
        [:xmlns "http://www.w3.org/2000/svg"
         :xmlns:xlink "http://www.w3.org/1999/xlink"
         :height ~height
         :width ~width]
        ~@body))

(defn brightness [col amt]
  (map (fn [x] (min 255 (max 0 (+ x amt))))
       col))

(defn svg-style [color]
  (let [adjusted-color (brightness color -100)]
    (format "fill:rgb(%d,%d,%d);stroke:rgb(%d,%d,%d)"
            (nth color 0) (nth color 1) (nth color 2)
            (nth adjusted-color 0) (nth adjusted-color 1) (nth adjusted-color 2))))

(defn circle [center radius color]
  (tag "circle" [:cx (first center)
                 :cy (second center)
                 :r radius
                 :style (svg-style color)]))

(defn polygon [points color]
  (let [points-str (apply str
                          (interpose " "
                                     (map (fn [[x y]] (str x "," y)) points)))
        style (svg-style color)]
    (tag "polygon"
         [:points points-str
          :style style])))

(defn random-walk   "Generate a random walk sequeance as a list, starting from a given value.

  ## Parameters:
  - value: The starting value for the random walk.
  - length: The number of steps in the random walk.

  ## Examples:
  ```clojure
  (random-walk 0 5)
  ;; (0 -1 0 -1 0)

  (random-walk 100 10)
  ;; (100 99 100 101 102 101 102 101 100 99)
  ```
"
  [value length]
  (when-not (zero? length)
    (cons value
          (random-walk (if (zero? (rand-int 2))
                         (dec value)
                         (inc value))
                       (dec length)))))

(defn save-svg-to-file [filename svg-content]
  (spit filename svg-content))

(def svg-content
  (svg 400 200
       (apply str (for  [_ (range 10)] (polygon (concat [[0 200]]
                                                        (map-indexed (fn [i n] [i n]) (random-walk 100 400))
                                                        [[400 200]])
                                                (repeatedly 3 #(rand-int 256)))))))

;; (save-svg-to-file "random-walk.svg" svg-content)


