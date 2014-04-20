(ns datomic-explanation.demo-5
  (:require [datomic.api :as d]))

(def uri "datomic:mem://my-database")
(d/create-database uri)
(def connection (d/connect uri))

(def my-database (d/db connection))

; Install :email as an attribute
(d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :email]
                        [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
                        [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
                        [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])

; Transact
(d/transact connection [[:db/add #db/id [:db.part/user -1] :db/ident :sara]
                        [:db/add #db/id [:db.part/user -1] :email "sara@example.com"]])

(d/q '[:find ?ident
       :in $ ?email
       :where
       [?id :email ?email]
       [?id :db/ident ?ident]]
     my-database
     "sara@example.com")

;=> #{[sara]}
