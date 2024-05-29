import type IBloque from "./IBloque";
import type IMensualidad from "./IMensualidad";
import type IVecino from "./IVecino";

export default interface IVivienda {
    id: number,
    bloque: IBloque,
    escalera: string,
    letra: string,
    mensualidad: IMensualidad | null,
    planta: string,
    puerta: string,
    vecino: IVecino,
    idMensualidad: number
    nombreVecino: string
}