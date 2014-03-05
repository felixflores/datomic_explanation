(ns datomic-explanation.demo-6
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo5")

(d/create-database uri)

(def connection (d/connect uri))
(def db-schema "resources/schema.edn")
(def schema (read-string (slurp db-schema)))

(d/transact connection schema)

(d/transact connection [{:db/id #db/id [:db.part/db -1]
                         :user/first-name "felix"
                         :user/last-name "flores"
                         :user/email "felix@neo.com"}])

(d/transact connection [[:db/add #db/id [:db.part/db -1] :user/first-name "romeo"]])

(def database (d/db connection))

;; (d/q '[:find ?e
;;        :where
;;        [?e :user/last-name "flores"]]
;;      database)
;;
;; (d/touch (d/entity database 66))



