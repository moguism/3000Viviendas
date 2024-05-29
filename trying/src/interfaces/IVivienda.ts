import type IBloque from "./IBloque";
import type IMensualidad from "./IMensualidad";
import type IVecino from "./IVecino";

export default interface IVivienda {
    id: number,
    bloque: IBloque,
    escalera: string | null,
    letra: string | null,
    mensualidad: IMensualidad,
    planta: string | null,
    puerta: string | null,
    vecino: IVecino,
    idMensualidad: number
    nombreVecino: string
}