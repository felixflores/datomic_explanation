(ns datomic-explanation.demo-3
  (:require [datomic.api :as d]))

(def uri "datomic:mem://my-database")
(d/create-database uri)
(def connection (d/connect uri))

; Install :email as an attribute
(d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :email]
                        [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
                        [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
                        [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])

(def my-database (d/db connection))
(:db.install/attribute (d/entity my-database :db.part/db))

;=> #{:db/noHistory :db.excise/before :db.excise/beforeT :db.install/partition :db/cardinality :db.install/attribute :db/index :db/excise :db/unique :db/fulltext :db.alter/attribute :db/txInstant :db/lang :email :db/doc :db.install/valueType :db.excise/attrs :db/code :db/isComponent :db/fn :db.install/function :db/valueType :db/ident :fressian/tag}

