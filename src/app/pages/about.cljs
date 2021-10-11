(ns app.pages.about
  (:require ["@fortawesome/free-brands-svg-icons/faDiscord" :refer [faDiscord]]
            ["@fortawesome/free-brands-svg-icons/faGithub" :refer [faGithub]]
            ["@fortawesome/free-brands-svg-icons/faLinkedin" :refer [faLinkedin]]
            ["@fortawesome/free-brands-svg-icons/faMonero" :refer [faMonero]]
            ["@fortawesome/free-solid-svg-icons/faEnvelope" :refer [faEnvelope]]
            ["@fortawesome/free-solid-svg-icons/faKey" :refer [faKey]]
            [app.components.common :as common]
            [app.data.global :as data]
            [reitit.frontend.easy :as rfe]))

(defn about-header
  [title subtitle]
  [:<>
   [:h1 {:class ["my-2" "sm:my-4" "text-3xl" "font-bold"]}
    title]
   [:h3 {:class ["italic" "my-2"]}
    subtitle]])

(defn about-page
  []
  [common/page-container
   [:<>
    [about-header "About"
     "My name is Christian Westrom, but everyone calls me West."]
    [:p "Over this past year I taught myself Clojure and ClojureScript, and I'd like to start working as a developer full-time."]
    [:p "You can check out my portfolio " [common/generic-link (rfe/href :app.data.router/projects) "here" :alt-target "_self"] ", and if you like what you see, please don't hesitate to send me a message."]
    [about-header "Contact"
     "You seem pretty sweet. We should get lunch sometime."]
    [:ul
     [common/icon-link (:email data/global-config)
      faEnvelope
      "Email address"
      :href (str "mailto:" (:email data/global-config))]
     [common/icon-link "wildwestrom"
      faGithub
      "Github"
      :href (:github data/global-config)]
     [common/icon-link "c-westrom"
      faLinkedin
      "Linkedin"
      :href (:linkedin data/global-config)]
     [common/icon-link (:discord data/global-config)
      faDiscord
      "Discord"
      :copyable true]
     [common/icon-link "Public PGP Key"
      faKey
      "Public PGP Key"
      :href (:pgp data/global-config)]]
    [about-header "Funding"
     "Because every site needs a \"give me money\" button."]
    [:ul
     [common/icon-link "Monero Wallet"
      faMonero
      "Monero wallet"
      :href (str "monero:" (:monero data/global-config))]]]])
