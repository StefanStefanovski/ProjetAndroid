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

        if(query.type == "1") {
          return   this.actualityRepository.find({
            where: {
              category: query.category.toLowerCase(),
            }
          });
        } else {
          return this.actualityRepository.find({
            where: {
              category: query.category.toLowerCase(),
              city: "Montpellier"
            }
          })
        }
  }

  @Get('/id/:id')
  async getCommerceById(@Query() query): Promise<any> {
    return this.actualityRepository.findOne(query.id);
  }

  @Get('categories')
  async getCommerceCategories() {
    return (await this.actualityRepository.find()).map(cat => {
      return cat.category
    });
  }

  @Post('add')
  async addCommerce(@Body() body) {
    return (await this.actualityRepository.insert(body));
  }


}
