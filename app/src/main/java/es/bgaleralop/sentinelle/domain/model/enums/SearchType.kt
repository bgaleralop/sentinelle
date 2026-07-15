/*
 *
 *  Copyright (C) 2026 Sentinelle Team <bgaleralop@gmail.com>
 *
 *  This source code is property of Sentinelle Team.
 *  It is made available publicly for portfolio evaluation and educational purposes only.
 *  Commercial use, reproduction, or distribution in any digital storefront is
 *  strictly prohibited under the PolyForm Noncommercial License 1.0.0.
 *
 *  For full license details, see the LICENSE.md file in the root directory.
 *
 */

package es.bgaleralop.sentinelle.domain.model.enums

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 15-07-2026
 *
 * Enumeración que recoge los diferentes tipos de búsqueda que se aplicarán a la hora de filtrar una
 * palabra.
 */
enum class SearchType {
    EXACT,
    CONTAINING
}