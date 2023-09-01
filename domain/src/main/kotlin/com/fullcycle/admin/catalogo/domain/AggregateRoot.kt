package com.fullcycle.admin.catalogo.domain

open class AggregateRoot<ID: Identifier> protected constructor(id: ID) : Entity<ID>(id) {
}