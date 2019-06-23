import { Moment } from 'moment';

export interface ICustomer {
  id?: number;
  name?: string;
  mobileNumber?: string;
  address?: string;
  email?: string;
  location?: string;
  createdDate?: Moment;
  createdBy?: string;
  purposeOfVisit?: string;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public name?: string,
    public mobileNumber?: string,
    public address?: string,
    public email?: string,
    public location?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public purposeOfVisit?: string
  ) {}
}
