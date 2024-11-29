(ns dice-of-doom.core-test
  (:require [clojure.test :refer :all]
            [dice-of-doom.core :refer :all]))

(deftest test-print-tag
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

(deftest test-tag-macro
  (testing "tag macro generates correct HTML tags"
    (let [output  (tag "a-tag" [:class "my-class"])]
      (is (= "<a-tag class=\"my-class\"></a-tag>" output)))

    (let [output  (tag "a-tag" '(:class "my-class"))]
      (is (= "<a-tag class=\"my-class\"></a-tag>" output)))))

(deftest test-html-macro
  (testing "html macro generates correct HTML tags"
    (let [output  (html)]
      (is (= "<html></html>" output)))

    (let [output  (html "Hello, HTML!")]
      (is (= "<html>Hello, HTML!</html>" output)))))

(deftest test-body-macro
  (testing "body macro generates correct HTML tags"
    (let [output  (body)]
      (is (= "<body></body>" output)))

    (let [output  (body "Hello, BODY!")]
      (is (= "<body>Hello, BODY!</body>" output)))))

(deftest test-html-and-body-macro
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
