import type IBloque from "./IBloque";
import type IVecino from "./IVecino";

export default interface IVivienda {
    id: number,
    bloque: IBloque,
    escalera: string | null,
    planta: string | null,
    puerta: string | null,
    letra: string | null,
    vecino: IVecino
}