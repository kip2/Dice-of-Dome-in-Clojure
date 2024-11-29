(ns dice-of-doom.core
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
