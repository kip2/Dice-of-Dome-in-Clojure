(ns dice-of-doom.core
  (:require [clojure.string :as str]))

(declare print-tag tag)

;; testcase
(println "1")
(print-tag "a-tag" '(("color" "blue") ("height" 9)) false)
(println "")

(println "2")
(print-tag "a-tag" '(("color" "blue") ("height" 9)) nil)
(println "")

(println "3")
(print-tag "a-tag" '(("color" "blue") ("height" 9)) true)
(println "")

(println "4")
(print-tag "a-tag" nil true)
(println "")

(defn print-tag [name atts closingp]
  (print (str "<" (if closingp "/" "") (str/lower-case name)))
  (doseq [[k v] atts]
    (print (str " " k "=\"" v "\"")))
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

(macroexpand-1 '(svg 100 100 "svg body"))

(svg 100 100 "svg body")
; <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" height="100" width="100">svg body</svg>





