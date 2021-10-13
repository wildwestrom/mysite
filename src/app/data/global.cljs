(ns app.data.global
  (:require [reagent.core :as reagent]))

(defonce current-page (reagent/atom nil))

(def global-config
  {:title       "Westrom.xyz - My Personal Page"
   :email       "c.westrom@westrom.xyz"
   :gitlab      "https://gitlab.com/wildwestrom"
   :github      "https://github.com/wildwestrom"
   :discord     "West#7965"
   :linkedin    "https://www.linkedin.com/in/c-westrom/"
   :monero      "899Lx4ix6NbFnCYj9y8LNm3QEw5CmuVv6SVEcHicRCgmRFBHKsRvL6G1xEsZhoBxN5abqfg2ioPtzfbaaHc1Yh9kFWbFBMf"
   :pgp         "https://keyserver.ubuntu.com/pks/lookup?search=0xD83B289EE4CF2879&fingerprint=on&op=index"
   :author      "Christian Westrom"
   :description "My site"
   :keywords    ["programming" "clojure" "clojurescript" "web development" "javascript"]})

(def nav-links
  [{:title "Home" :href :app.data.router/home}
   {:title "Blog" :href :app.data.router/blog}
   {:title "Portfolio" :href :app.data.router/portfolio}])
