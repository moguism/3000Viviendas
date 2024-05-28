import type IVecino from "./IVecino";

export default interface IRol {
    id: number,
    nombre: string,
    vecinos: Array<IVecino>
}