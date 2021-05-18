(ns app.index
  (:require [hiccup.page :as hiccup]
            [clojure.string :as string]
            [clojure.edn :as edn]))

(def noscript-msg? true)

(def data
  (edn/read-string (slurp "src/config/site-data.edn")))

(defn index-html
  []
  (hiccup/html5 {:lang "en"}
                [:head {}
                 [:meta {:charset "UTF-8"}]
                 [:meta {:name    "description",
                         :content (:description data)}]
                 [:meta {:name    "keywords",
                         :content (string/join "," (:keywords data))}]
                 [:meta {:name    "author",
                         :content (:author data)}]
                 [:meta {:name    "viewport",
                         :content "width=device-width, initial-scale=1.0"}]
                 [:title {} (:title data)]
                 [:link {:rel  "stylesheet",
                         :href "/css/main.css"}]]
                [:body
                 [:div {:id "app"}]
                 [:script {:src "/js/compiled/main.js", :type "text/javascript"}]
                 (if noscript-msg?
                   [:noscript {} "You need to enable JavaScript to run this app."]
                   nil)]))

(defn html-404
  []
  (hiccup/html5 {:lang "en"}
                [:head {}
                 [:meta {:name    "description"
                         :content "404 - Not Found"}]
                 [:meta {:name    "viewport",
                         :content "width=device-width, initial-scale=1.0"}]
                 [:title {} (str (:title data) "404")]
                 [:link {:rel  "stylesheet",
                         :href "/css/main.css"}]]
                [:body
                 [:div.404
                  [:h1 "404: File not found"]]]))

(spit "./public/index.html" (index-html))
(spit "./public/404.html" (html-404))
