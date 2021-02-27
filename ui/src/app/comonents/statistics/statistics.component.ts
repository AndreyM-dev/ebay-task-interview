import {Component, OnDestroy} from '@angular/core';
import {StatisticsService} from '../../services/statistics.service';
import {Subscription} from 'rxjs';
import {ConditionSummary} from '../../types/condition-summary.interface';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnDestroy {
  public searchValue: string;
  public isShowReport: boolean;
  public reportName: string;
  public conditionSummary: ConditionSummary;
  private readonly statisticsSubscriptions: Subscription = new Subscription();

  constructor(private statisticsService: StatisticsService) {
  }

  private getStatisticsData(accountManager: string): void {
    const statisticsDataSub: Subscription = this.statisticsService
      .getStatisticsData(accountManager)
      .subscribe((res) => {
        this.reportName = res.fullName;
        this.conditionSummary = res.conditionSummaryMap;
        this.isShowReport = true;
      });

    this.statisticsSubscriptions.add(statisticsDataSub);
  }

  public find(): void {
    if (this.searchValue.length) {
      const accountManager = this.searchValue.trim();
      this.getStatisticsData(accountManager);
    }
  }

  ngOnDestroy(): void {
    this.statisticsSubscriptions.unsubscribe();
  }
}
