(ns app.data
  (:require [clojure.edn :as edn]
            [reagent.core :as reagent]
            [shadow.resource]
            [lambdaisland.fetch :as fetch]
            [kitchen-async.promise :as p]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; State
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce match (reagent/atom nil))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Blog Post Fetcher
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def posts-route "posts/")

(defn uri [filestr]
  (str posts-route filestr))

(def all-posts-uri (uri "_ALL_POSTS.edn"))

(defn store-posts-data
  [a]
  (let [extract-body (fn [res] (edn/read-string (:body res)))]
    (p/let [filenames  (fetch/get all-posts-uri)
            files      (extract-body filenames)
            files-resp (p/all (map #(fetch/get (uri %)) files))]
      (reset! a (reverse (map extract-body files-resp)))
      (js/console.debug "Received blog-posts"))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Other stuff
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def global-config
  (edn/read-string (shadow.resource/inline "config/site-data.edn")))

(def portfolio
  (edn/read-string (shadow.resource/inline "config/portfolio.edn")))
