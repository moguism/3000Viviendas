import type IBloque from "./IBloque";
import type IMensualidad from "./IMensualidad";
import type IVecino from "./IVecino";

export default interface IVivienda {
    id: number,
    bloque: IBloque,
    escalera: string,
    planta: string,
    puerta: string,
    letra: string,
    vecino: IVecino,
    ultima_mensualidad: IMensualidad
    nombreVecino: string
}