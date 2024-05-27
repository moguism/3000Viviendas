import type IVecino from "./IVecino";

export default interface IMensualidad {
    id: number,
    fecha: Date,
    cuantia: number,
    vecinos: Array<IVecino>
}