import { Controller, Post, Body, Get, Param, Query } from '@nestjs/common';
import { AuthService } from  '../services/auth.service';
import { User } from  '../entities/user.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Actuality } from '../entities/actuality.entity';
import { Category } from '../entities/comerce.entity';


@Controller('commerce')
export  class  CommerceController {
  constructor(@InjectRepository(Category)
              private actualityRepository: Repository<Category>,) {}

  @Get('')
  async getCommerce(@Query() query): Promise<any> {
    return this.actualityRepository.find({
      where: {
        category: query.type.toLowerCase(),
      }
    })
  }

  @Get('categories')
  async getCommerceCategories() {
    return (await this.actualityRepository.find()).map(cat => {
      return cat.category
    });
  }


}
