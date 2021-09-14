(ns app.pages
  (:require ["@fortawesome/free-brands-svg-icons" :refer [faGithub faLinkedin faDiscord faMonero]]
            ["@fortawesome/free-solid-svg-icons" :refer [faEnvelope faKey]]
            [reagent.core :as reagent]
            [app.data :as data]
            [app.components :as components]
            [reitit.frontend.easy :as rfe]))

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
       :href (str "mailto:" (:email data/global-config))
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
   [:div.max-w-prose
    [:h1 {:class ["text-3xl" "font-bold"]} "Complete and Ongoing Projects:"]
    [:div#project-1.mt-4
     [:h2 {:class ["text-2xl"]} "This Website"]
     [:ul
      [:li
       [components/generic-link "https://github.com/wildwestrom/mysite" "source"]]]
     [:p "This project started as an experiment in repl-driven-development, and is now the epicenter of my online presence. It's been really fun working with Reagent and learning more about how to use components from React."]]
    [:div#project-2.mt-4
     [:h2 {:class ["text-2xl"]} "Uniorg-Util"]
     [:ul
      [:li
       [components/generic-link "https://github.com/wildwestrom/uniorg-util" "source"]]
      [:li
       [components/generic-link "https://www.npmjs.com/package/uniorg-util" "npm link"]]]
     [:p "This program converts Org Mode documents into HTML and metadata as either JSON or EDN. It relies heavily on rasendubi’s very accurate Org Mode parser, uniorg."]
     [:p "This tool is just one part of what makes my blog work."]]]])

(defn not-found-page
  []
  [:h2 {:class ["text-5xl" "p-4" "font-mono"]}
   "Error:" [:br] "Page not found."])

(defn app
  []
  [:div {:class ["text-gray-700" "bg-gray-50"
                 "subpixel-antialiased" "min-h-screen"]}
   [:div.min-h-screen.flex.flex-col
    [components/navbar (mapv (fn [eachmap] (assoc eachmap :href (rfe/href (:href eachmap))))
                             [{:title "Home" :href :app.router/home}
                              {:title "Projects" :href :app.router/projects}
                              {:title "Blog" :href :app.router/blog}
                              {:title "About" :href :app.router/about}])]
    [:div.grid.flex-grow
     (if @data/match
       (let [view (:view (:data @data/match))]
         [view @data/match])
       [not-found-page])]]
   [components/license]])
