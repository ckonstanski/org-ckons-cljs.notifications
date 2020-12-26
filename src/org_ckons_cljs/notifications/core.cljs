(ns org-ckons-cljs.notifications.core
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [dommy.core :as dommy]))
            
(declare template-message)
(declare template-error)
(declare maybe-error)
(declare maybe-message)

(hiccups/defhtml template-message [message]
  [:div {:class "alert alert-success"} message])

(hiccups/defhtml template-error [errormsg]
  [:div {:class "alert alert-danger"} errormsg])

(defn maybe-message [jsonobj]
  (when (get jsonobj "locationP")
    (let [message (get jsonobj "message")]
      (cond (empty? message)
            (dommy/set-style! (dommy/sel1 :#message) :display "none")
            :else
            (do
              (dommy/set-style! (dommy/sel1 :#message) :display "block")
              (dommy/set-html! (dommy/sel1 :#message)
                               (template-message message)))))))

(defn maybe-error [jsonobj]
  (when (get jsonobj "locationP")
    (let [errormsg (get jsonobj "errormsg")]
      (cond (empty? errormsg)
            (dommy/set-style! (dommy/sel1 :#errormsg) :display "none")
            :else
            (do
              (dommy/set-style! (dommy/sel1 :#errormsg) :display "block")
              (dommy/set-html! (dommy/sel1 :#errormsg)
                               (template-error errormsg)))))))
