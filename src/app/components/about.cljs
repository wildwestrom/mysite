(ns app.components.about
  (:require [app.components.common :as common]
            [app.data :as data]
            ["@fortawesome/free-brands-svg-icons" :refer [faGithub faLinkedin faDiscord faMonero]]
            ["@fortawesome/free-solid-svg-icons" :refer [faEnvelope faKey]]))

(defn about-header
  [title subtitle]
  [:<>
   [:h1 {:class ["pb-2" "sm:py-4" "text-3xl" "font-bold"]}
    title]
   [:h2 {:class ["italic" "pb-2"]}
    subtitle]])

(defn about-page
  []
  [:div {:class ["py-2" "px-2" "xs:px-8" "flex" "flex-col" "flex-grow"
                 "self-start" "justify-self-center" "overflow-auto"]}
   [about-header "Contact"
    "You seem pretty sweet. We should get lunch sometime."]
   [:ul
    [common/icon-link (:email data/global-config)
     faEnvelope
     "Email address"
     :href (str "mailto:" (:email data/global-config))
     :mail true]
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
     :href (str "monero:" (:monero data/global-config))]]])
