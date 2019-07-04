import { Moment } from 'moment';

export interface ICustomerdetail {
  id?: number;
  serviceType?: string;
  sizeOfPlot?: string;
  constructionType?: string;
  buildingType?: string;
  soilType?: string;
  noOfRoomsRequired?: string;
  expectedEndDate?: Moment;
  budGet?: string;
  createdDate?: Moment;
  createdBy?: string;
}

export class Customerdetail implements ICustomerdetail {
  constructor(
    public id?: number,
    public serviceType?: string,
    public sizeOfPlot?: string,
    public constructionType?: string,
    public buildingType?: string,
    public soilType?: string,
    public noOfRoomsRequired?: string,
    public expectedEndDate?: Moment,
    public budGet?: string,
    public createdDate?: Moment,
    public createdBy?: string
  ) {}
}
