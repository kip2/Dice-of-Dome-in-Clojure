(ns dice-of-doom.core-test
  (:require [clojure.test :refer :all]
            [dice-of-doom.core :refer :all]))

(deftest print-tag-test
  (testing "print-tag generates correct HTML tags"
    (let [output  (print-tag "div" {:class "my-class" :id "main"} false)]
      (is (= "<div class=\"my-class\" id=\"main\">" output)))

    (let [output  (print-tag "DIV" {:class "my-class" :id "main"} true)]
      (is (= "</div class=\"my-class\" id=\"main\">" output)))

    (let [output  (print-tag "span" {} false)]
      (is (= "<span>" output)))

    (let [output  (print-tag "BR" {} true)]
      (is (= "</br>" output)))

    (let [output  (print-tag "a-tag" '(("color" "blue") ("height" 9)) false)]
      (is (= "<a-tag color=\"blue\" height=\"9\">" output)))

    (let [output  (print-tag "a-tag" '(("color" "blue") ("height" 9)) nil)]
      (is (= "<a-tag color=\"blue\" height=\"9\">" output)))

    (let [output  (print-tag "a-tag" '(("color" "blue") ("height" 9)) true)]
      (is (= "</a-tag color=\"blue\" height=\"9\">" output)))

    (let [output  (print-tag "a-tag" nil true)]
      (is (= "</a-tag>" output)))))

(deftest tag-macro-test
  (testing "tag macro generates correct HTML tags"
    (let [output  (tag "a-tag" [:class "my-class"])]
      (is (= "<a-tag class=\"my-class\"></a-tag>" output)))

    (let [output  (tag "a-tag" '(:class "my-class"))]
      (is (= "<a-tag class=\"my-class\"></a-tag>" output)))))

(deftest html-macro-test
  (testing "html macro generates correct HTML tags"
    (let [output  (html)]
      (is (= "<html></html>" output)))

    (let [output  (html "Hello, HTML!")]
      (is (= "<html>Hello, HTML!</html>" output)))))

(deftest body-macro-test
  (testing "body macro generates correct HTML tags"
    (let [output  (body)]
      (is (= "<body></body>" output)))

    (let [output  (body "Hello, BODY!")]
      (is (= "<body>Hello, BODY!</body>" output)))))

(deftest html-and-body-macro-test
  (testing "Test the combination of the html macro and body macro"
    (let [output (html (body "Hello, HTML!"))]
      (is (= "<html><body>Hello, HTML!</body></html>" output)))))

(deftest svg-macro-test
  (testing "svg macro generates correct svg tags"
    (let [output (svg 100 100 "svg body")]
      (is (= "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"100\" width=\"100\">svg body</svg>" output)))))

(deftest brightness-test
  (testing "brightness function adjusts color values within the valid range."
    (let [color (brightness '(255 0 0) -100)]
      (is (= '(155 0 0) color)))

    (let [color (brightness '(0 255  0) -100)]
      (is (= '(0 155 0) color)))

    (let [color (brightness '(0 0 255) -100)]
      (is (= '(0 0 155) color)))

    (let [color (brightness '(0 0 0) -100)]
      (is (= '(0 0 0) color)))

    (let [color (brightness '(255 255 255) 100)]
      (is (= '(255 255 255) color)))))

(deftest svg-style-test
  (testing "svg-style function generage svg style strings."
    (let [style (svg-style [50 50 50])]
      (is (= "{fill:rgb(50,50,50);stroke:rgb(0,0,0)}" style)))

    (let [style (svg-style [150 150 150])]
      (is (= "{fill:rgb(150,150,150);stroke:rgb(50,50,50)}" style)))

    (let [style (svg-style [255 255 255])]
      (is (= "{fill:rgb(255,255,255);stroke:rgb(155,155,155)}" style)))))

(deftest circle-test
  (testing ""
    (let [circle-svg (circle [50 50] 50 [255 0 0])]
      (is (= "<circle cx=\"50\" cy=\"50\" r=\"50\" style=\"{fill:rgb(255,0,0);stroke:rgb(155,0,0)}\"></circle>" circle-svg)))))

(deftest polygon-test
  (testing
   (let [polygon-svg (polygon [[10 20] [30 40]] [50 50 50])]
     (is (= "<polygon points=\"10,20 30,40\" style=\"{fill:rgb(50,50,50);stroke:rgb(0,0,0)}\"></polygon>" polygon-svg)))))


