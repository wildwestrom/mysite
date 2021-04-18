(ns app.index
  (:require [hiccup.page :as hiccup]
            [clojure.string :as string]
            [app.data :as data]))

(def noscript? false)

(defn index-html
  []
  (hiccup/html5 {:lang "en" :id "root"}
                [:head {}
                 [:meta {:charset "UTF-8"}]
                 [:meta {:name    "description",
                         :content data/description}]
                 [:meta {:name    "keywords",
                         :content (string/join "," data/keywords)}]
                 [:meta {:name    "author",
                         :content data/author}]
                 [:meta {:name    "viewport",
                         :content "width=device-width, initial-scale=1.0"}]
                 [:title {} data/title]
                 [:link {:rel  "stylesheet",
                         :href "/css/main.css"}]]
                [:body {:id "body"}
                 (if noscript?
                   [:noscript {} "You need to enable JavaScript to run this app."]
                   nil)
                 [:div {:id "app"}]
                 [:script {:src "/js/main.js", :type "text/javascript"}]]))

(spit "./public/index.html" (index-html))
