(ns dice-of-doom.core
  (:require [clojure.string :as str]
            [clojure.test :refer :all]))

(defn print-tag [tag-name atts closingp]
  (print (str "<" (if closingp "/" "") (str/lower-case tag-name)))
  (doseq [[k v] atts]
    (print (str " " (name k) "=\"" v "\"")))
  (print ">"))

(defmacro tag [name atts & body]
  `(do
     (print-tag '~name
                (map (fn [[k# v#]]
                       [(name k#) v#])
                     (partition 2 ~atts))
                nil)
     (print ~@body)
     (print-tag ~name nil true)))

(defmacro html [& body]
  `(tag "html" []
        ~@body))

(defmacro body [& body]
  `(tag "body" []
        ~@body))

;; todo: rewrite this code into test case.
(html
 (body
  "Hello, HTML!"))
; <html><body>Hello, HTML!</body></html>

(defmacro svg [width height & body]
  `(tag "svg"
        [:xmlns "http://www.w3.org/2000/svg"
         :xmlns:xlink "http://www.w3.org/1999/xlink"
         :height ~height
         :width ~width]
        ~@body))

;; todo: rewrite this code into test case.
(svg 100 100 "svg body")
; <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" height="100" width="100">svg body</svg>





