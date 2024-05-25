import type IBloque from '../interfaces/IBloque.ts'
import type IContrato from './IContrato.js';
import type IDeuda from './IDeuda.js';
import type IGasto from './IGasto.js';
import type IIngreso from './IIngreso.js';
import type ILocal from './ILocal.js';

export default interface ICommunity {
    id: number,
    nombre: string,
    direccion: string,
    bloques: Array<IBloque>,
    contratos: Array<IContrato>,
    locales: Array<ILocal>,
    deudas: Array<IDeuda>
    ingresos: Array<IIngreso>,
    gastos: Array<IGasto>;
}