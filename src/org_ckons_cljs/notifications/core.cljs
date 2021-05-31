(ns org-ckons-cljs.notifications.core
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]
            [dommy.core :as dommy]))

(declare template-message)
(declare template-errormsg)
(declare message-div-or-nil)
(declare errormsg-div-or-nil)
(declare maybe-message)
(declare maybe-error)

(hiccups/defhtml template-message [message]
  [:div {:class "alert alert-success"} message])

(hiccups/defhtml template-error [errormsg]
  [:div {:class "alert alert-danger"} errormsg])

(defn message-div-or-nil []
  (when (not (empty? (dommy/html (dommy/sel1 :#message))))
    (dommy/html (dommy/sel1 :#message))))

(defn errormsg-div-or-nil []
  (when (not (empty? (dommy/html (dommy/sel1 :#errormsg))))
    (dommy/html (dommy/sel1 :#errormsg))))

;; loc-p | msg | msg-div || output
;; -------------------------------------
;; f     | f   | f       || nil
;; f     | f   | t       || msg-div
;; f     | t   | -       || msg
;; t     | f   | -       || nil
;; t     | t   | -       || msg
;;
(defn maybe-message [jsonobj]
  (let [location-p (get jsonobj "locationP")
        message (get jsonobj "message")
        message-div (dommy/html (dommy/sel1 :#message))]
    (dommy/set-html! (dommy/sel1 :#message)
                     (cond (or (and location-p
                                    message)
                               (and (not location-p)
                                    message))
                           (template-message message)
                           (and (not location-p)
                                (not message)
                                (message-div-or-nil))
                           (message-div-or-nil)
                           :else
                           ""))
    (dommy/set-style! (dommy/sel1 :#message)
                      :display (cond (empty? (dommy/html (dommy/sel1 :#message)))
                                     "none"
                                     :else
                                     "block"))))

;; loc-p | err | err-div || output
;; -------------------------------------
;; f     | f   | f       || nil
;; f     | f   | t       || err-div
;; f     | t   | -       || err
;; t     | f   | -       || nil
;; t     | t   | -       || err
;;
(defn maybe-error [jsonobj]
  (let [location-p (get jsonobj "locationP")
        errormsg (get jsonobj "errormsg")
        errormsg-div (dommy/html (dommy/sel1 :#errormsg))]
    (dommy/set-html! (dommy/sel1 :#errormsg)
                     (cond (or (and location-p
                                    errormsg)
                               (and (not location-p)
                                    errormsg))
                           (template-error errormsg)
                           (and (not location-p)
                                (not errormsg)
                                (errormsg-div-or-nil))
                           (errormsg-div-or-nil)
                           :else
                           ""))
    (dommy/set-style! (dommy/sel1 :#errormsg)
                      :display (cond (empty? (dommy/html (dommy/sel1 :#errormsg)))
                                     "none"
                                     :else
                                     "block"))))
