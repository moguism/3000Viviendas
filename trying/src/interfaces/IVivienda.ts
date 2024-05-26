import type IBloque from "./IBloque";
import type IVecino from "./IVecino";

export default interface IVivienda {
    id: number,
    bloque: IBloque,
    escalera: string,
    planta: string,
    puerta: string,
    letra: string,
    vecino: IVecino,
    nombreVecino: string
}