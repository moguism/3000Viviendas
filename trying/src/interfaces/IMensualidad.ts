import type IVivienda from "./IVivienda";

export default interface IMensualidad {
    id: number,
    fecha: Date,
    cuantia: number,
    viviendas: Array<IVivienda>
}