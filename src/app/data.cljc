(ns app.data
  (:require [org-parser.parser :refer [org]]))

(def data
  {:title       "West's Personal Website"
   :email       "c.westrom@westrom.xyz"
   :gitlab      "https://gitlab.com/wildwestrom"
   :github      "https://github.com/wildwestrom"
   :discord     "@West#7965"
   :author      "Christian Westrom"
   :year        "2021"
   :description "West's Site: A blog, projects, CV, and more."
   :keywords    '["programming" "clojure" "clojurescript" "web development" "javascript"]
   })
