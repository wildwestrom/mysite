(ns app.data
  (:require [clojure.edn :as edn]
            [reagent.core :as r]
            [shadow.resource]
            ;; [ajax.core :refer [GET json-response-format]]
            [clojure.core.async :as a]
            ;; [cljs.core.async.interop :refer-macros [<p!]]
            ;; [superv.async :refer [S] :refer-macros [<?*]]
            ))

(def data
  (edn/read-string (shadow.resource/inline "config/site-data.edn")))

(def dev-root "http://localhost:3000/")
(def post-route "posts/")
(defn uri [filestr]
  (str dev-root post-route filestr ".json"))

(def all-posts-uri (uri "_ALL_POSTS"))

;; (defn get-json
;;   [uri]
;;   (->
;;    (js/fetch uri)
;;    (.then (fn [res] (js->clj (.json res) :keywordize-keys :true)))
;;    (.catch (fn [err] (.log js/console err)))))

;; (defn get-blog-posts
;;   []
;;   (let [out (async/chan)]
;;     (go (-> (get-json all-posts-uri)
;;             (.then (fn [posts]
;;                      (doseq [post posts]
;;                        (let [post-uri (uri post)]
;;                          (->
;;                           (get-json post-uri)
;;                           (.then (fn [post] (put! out post))))))))))))

(def temp-1 (r/atom ()))

(defn swap-async!
  [atom chan fun & args]
  (a/go
    (let [val (a/<! chan)]
      (apply swap! atom fun val args))))


;; (defn get-all-posts
;;   []
;;   (go
;;     (try
;;       (let [all-posts (js->clj (<p! (.json (<p! (js/fetch all-posts-uri)))) :keywordize-keys :true)]
;;         (<?* S
;;              (for [post all-posts]
;;                (go
;;                  (js->clj (<p! (.json (<p! (js/fetch (uri post))))) :keywordize-keys :true)))))
;;       (catch js/Error err (js/console.log err)))))

(js/console.log (GET all-posts-uri))

;; (swap-async! temp-1 )

#_(deref temp-1)
