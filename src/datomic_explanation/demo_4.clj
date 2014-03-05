(ns datomic-explanation.demo-4
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo4")

(d/create-database uri)

(def connection (d/connect uri))

(def database (d/db connection))

;; (d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :user/first-name]
;;                         [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
;;                         [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
;;                         [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]
;;                         [:db/add #db/id [:db.part/db -2] :db/ident :user/last-name]
;;                         [:db/add #db/id [:db.part/db -2] :db/cardinality :db.cardinality/one]
;;                         [:db/add #db/id [:db.part/db -2] :db/valueType :db.type/string]
;;                         [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -2]]
;;                         [:db/add #db/id [:db.part/db -3] :db/ident :user/email]
;;                         [:db/add #db/id [:db.part/db -3] :db/cardinality :db.cardinality/one]
;;                         [:db/add #db/id [:db.part/db -3] :db/valueType :db.type/string]
;;                         [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -3]]])
;;
;; (d/transact connection [[:db/add #db/id [:db.part/user -1] :user/first-name "felix"]
;;                         [:db/add #db/id [:db.part/user -1] :user/last-name "flores"]
;;                         [:db/add #db/id [:db.part/user -1] :user/email "felix@neo.com"]])
;;
;; (d/transact connection [{:db/id #db/id [:db.part/db]
;;                          :user/first-name "felix"
;;                          :user/last-name "flores"
;;                          :user/email "felix@neo.com"}])
;;
;; (d/transact connection [{:db/id #db/id[:db.part/db]
;;                          :db/ident :user/first-name
;;                          :db/valueType :db.type/string
;;                          :db/cardinality :db.cardinality/one
;;                          :db.install/_attribute :db.part/db}
;;
;;                         {:db/id #db/id[:db.part/db]
;;                          :db/ident :user/last-name
;;                          :db/valueType :db.type/string
;;                          :db/cardinality :db.cardinality/one
;;                          :db.install/_attribute :db.part/db}
;;
;;                         {:db/id #db/id[:db.part/db]
;;                          :db/ident :user/email
;;                          :db/valueType :db.type/string
;;                          :db/cardinality :db.cardinality/many
;;                          :db.install/_attribute :db.part/db}])
;;
;; (def db-schema "resources/schema.edn")
;; (def schema (read-string (slurp db-schema)))
;; (d/transact connection schema)

