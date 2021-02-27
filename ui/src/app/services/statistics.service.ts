import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Statistics} from '../types/statistics.interface';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {
  private pathToApi = environment.baseUrl;

  constructor(private httpClient: HttpClient) {
  }

  getStatisticsData(accountManager: string): Observable<Statistics> {
    const url = `${this.pathToApi}/statistic/${accountManager}`;
    return this.httpClient.get<Statistics>(url);
  }
}
