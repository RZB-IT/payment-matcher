package cz.teddy.matcher.jpa.entity

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Objects

@Entity
@Table(name = "matching_target", schema = "public")
class MatchingTargetEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    var id: Int = 0

    @Basic
    @Column(name = "first_name")
    var firstName: String? = null

    @Basic
    @Column(name = "last_name")
    var lastName: String? = null

    @Basic
    @Column(name = "address")
    var address: String? = null

    @Basic
    @Column(name = "iban")
    var iban: String? = null

    @Basic
    @Column(name = "variable_symbol")
    var variableSymbol: String? = null

    @Basic
    @Column(name = "specific_symbol")
    var specificSymbol: String? = null

    @Basic
    @Column(name = "constant_symbol")
    var constantSymbol: String? = null

    @Basic
    @Column(name = "reference")
    var reference: String? = null

    @Basic
    @Column(name = "identifier")
    var identifier: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as MatchingTargetEntity
        return id == that.id && firstName == that.firstName && lastName == that.lastName && address == that.address && iban == that.iban && variableSymbol == that.variableSymbol && specificSymbol == that.specificSymbol && constantSymbol == that.constantSymbol && reference == that.reference && identifier == that.identifier
    }

    override fun hashCode(): Int {
        return Objects.hash(id, firstName, lastName, address, iban, variableSymbol, specificSymbol, constantSymbol, reference, identifier)
    }
}
