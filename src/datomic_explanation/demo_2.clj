(ns datomic-explanation.demo-2
  (:require [datomic.api :as d]))

(def uri "datomic:mem://my-database")
(d/create-database uri)
(def connection (d/connect uri))
(def database (d/db connection))

; Install :email as an attribute
(d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :email]
                        [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
                        [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
                        [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])

;; ; Attempt to transact the following two datoms
(d/transact connection [[:db/add #db/id [:db.part/user -1] :db/ident "John Doe"]
                        [:db/add #db/id [:db.part/user -1] :email "doe@example.com"]])
