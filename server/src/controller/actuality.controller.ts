import { Controller, Post, Body, Get, Param, Query } from '@nestjs/common';
import { AuthService } from  '../services/auth.service';
import { User } from  '../entities/user.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Actuality } from '../entities/actuality.entity';


@Controller('actuality')
export  class  ActualityController {
  constructor(@InjectRepository(Actuality)
              private actualityRepository: Repository<Actuality>,) {}

  @Get('')
  async getActuality(@Query() query): Promise<any> {
    console.dir(query)
    return this.actualityRepository.find({
      where: {
        category: query.type.toLowerCase(),
      }
    })
  }


}
