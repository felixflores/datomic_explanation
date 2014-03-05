(ns datomic-explanation.demo-3
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo3")

(d/create-database uri)

(def connection (d/connect uri))

(def database (d/db connection))

(d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :user/first-name]
                        [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
                        [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
                        [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])

;; (d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :user/first-name]
;;                         [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
;;                         [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
;;                         [:db/add 0 :db.install/attribute #db/id [:db.part/db -1]]])

(d/transact connection [[:db/add #db/id [:db.part/user -1] :user/first-name "felix"]])
