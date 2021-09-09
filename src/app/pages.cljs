(ns app.pages
  (:require ["@fortawesome/free-brands-svg-icons" :refer [faGithub faLinkedin faDiscord faMonero]]
            ["@fortawesome/free-solid-svg-icons" :refer [faEnvelope faKey]]
            [reagent.core :as reagent]
            [app.data :as data]
            [app.components :as components]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialization
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce blog-posts (reagent/atom nil))

(data/store-posts-data blog-posts)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pages
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn home-page
  []
  [:h2 {:class ["text-5xl" "p-4" "italic" "justify-self-center" "self-center"]}
   "Welcome," [:br {:class ["block" "sm:hidden"]}] " to my site!"])

(defn blog-preview-page
  []
  [:div {:class ["max-w-prose" "pt-2" "items-stretch" "self-start" "justify-self-center"]}
   (for [blog-post @blog-posts]
     ^{:key (-> blog-post :meta :id)}
     [components/blog-post-preview blog-post])])

(defn blog-post-page
  []
  (let [id   (->> @data/match :parameters :path :id)
        post (first (filter #(= id (-> % :meta :id)) @blog-posts))]
    [components/blog-post-main-view post]))

(defn about-page
  []
  (let [about-header
        (fn [title subtitle]
          [:<>
           [:h1 {:class ["pb-2" "sm:py-4" "text-3xl" "font-bold"]}
            title]
           [:h2 {:class ["italic" "pb-2"]}
            subtitle]])]
    [:div {:class ["py-2" "px-2" "xs:px-8" "flex" "flex-col" "flex-grow"
                   "self-start" "justify-self-center" "overflow-auto"]}
     [about-header "Contact"
      "You seem pretty sweet. We should get lunch sometime."]
     [:ul
      [components/icon-link (:email data/global-config)
       faEnvelope
       "Email address"
       :href (:email data/global-config)
       :mail true]
      [components/icon-link "wildwestrom"
       faGithub
       "Github"
       :href (:github data/global-config)]
      [components/icon-link "c-westrom"
       faLinkedin
       "Linkedin"
       :href (:linkedin data/global-config)]
      [components/icon-link (:discord data/global-config)
       faDiscord
       "Discord"
       :copyable true]
      [components/icon-link "Public PGP Key"
       faKey
       "Public PGP Key"
       :href (:pgp data/global-config)]]
     [about-header "Funding"
      "Because every site needs a \"give me money\" button."]
     [:ul
      [components/icon-link "Monero Wallet"
       faMonero
       "Monero wallet"
       :href (str "monero:" (:monero data/global-config))]]]))

(defn projects-page
  []
  [:div {:class ["justify-self-center" "self-start" "sm:mt-2" "p-4"]}
   [:div#project-1.max-w-prose
   [:h1 {:class ["text-3xl" "font-bold"]} "Projects:"]
   [:h2 {:class []} "This Website"]
   [:ul
    [:li "Source"]]
   [:p "This project started as an experiment in repl-driven-development, and is now the epicenter of my online presence. It's been really fun working with Reagent and learning more about how to use components from React."]]
   ])

(defn not-found-page
  []
  [:h2 {:class ["text-5xl" "p-4" "font-mono"]}
   "Error:" [:br] "Page not found."])

(defn app
  []
  [:div {:class ["text-gray-700" "bg-gray-50"
                 "subpixel-antialiased" "min-h-screen"]}
   [:div.min-h-screen.flex.flex-col
    [components/navbar]
    [:div.grid.flex-grow
     (if @data/match
       (let [view (:view (:data @data/match))]
         [view @data/match])
       [not-found-page])]]
   [components/license]])
